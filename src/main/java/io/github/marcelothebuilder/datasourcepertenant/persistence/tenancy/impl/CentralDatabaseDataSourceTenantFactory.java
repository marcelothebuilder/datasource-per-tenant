package io.github.marcelothebuilder.datasourcepertenant.persistence.tenancy.impl;

import io.github.marcelothebuilder.datasourcepertenant.model.common.Company;
import io.github.marcelothebuilder.datasourcepertenant.model.common.User;
import io.github.marcelothebuilder.datasourcepertenant.repository.common.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class CentralDatabaseDataSourceTenantFactory implements DataSourceTenantFactory {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyDataSourceInitializer companyDataSourceInitializer;

    private Map<Long, DataSource> dataSources = new ConcurrentHashMap<>();

    @Override
    @Transactional(readOnly = true)
    public DataSource getDataSource() {
        log.info("repository state: not null? {}", userRepository != null);

        if (isSecurityContextEmpty()) {
            return new NullDataSource();
        }

        Company company = getSecurityContextCompany();

        return resolveCompanyDataSource(company);
    }

    private DataSource resolveCompanyDataSource(Company company) {
        if (dataSources.containsKey(company)) {
            return dataSources.get(company.getId());
        }

        DataSource dataSource = initializerCompanyDataSource(company);
        dataSources.put(company.getId(), dataSource);
        return dataSource;
    }

    private DataSource initializerCompanyDataSource(Company company) {
        return companyDataSourceInitializer.initialize(company);
    }

    private Company getSecurityContextCompany() {
        String email = (String) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        User user = userRepository.findByEmail(email);
        return user.getCompany();
    }

    private boolean isSecurityContextEmpty() {
        if (SecurityContextHolder.getContext() == null) {
            return true;
        }

        return SecurityContextHolder.getContext().getAuthentication() == null;
    }
}
