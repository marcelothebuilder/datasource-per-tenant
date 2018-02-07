package io.github.marcelothebuilder.datasourcepertenant.persistence.tenancy.impl;


import io.github.marcelothebuilder.datasourcepertenant.model.common.Company;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class SimpleCompanyDataSourceInitializer implements CompanyDataSourceInitializer {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public DataSource initialize(Company company) {
        String connection = company.getJdbcConnection();
        val driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUrl(connection);
        driverManagerDataSource.setPassword("");
        driverManagerDataSource.setUsername("sa");
        driverManagerDataSource.setDriverClassName(org.h2.Driver.class.getName());

        applicationEventPublisher.publishEvent(new TenantDataSourceInitializedEventImpl(driverManagerDataSource));

        return driverManagerDataSource;
    }
}
