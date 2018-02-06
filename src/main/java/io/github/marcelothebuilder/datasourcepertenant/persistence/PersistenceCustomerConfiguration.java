package io.github.marcelothebuilder.datasourcepertenant.persistence;

import io.github.marcelothebuilder.datasourcepertenant.repository.customer.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@Configuration
@EnableTransactionManagement
@Slf4j
@EnableJpaRepositories(basePackageClasses = AddressRepository.class,
        transactionManagerRef = "customerTransactionManager",
        entityManagerFactoryRef = "customerEntityManagerFactory"
)
public class PersistenceCustomerConfiguration {

    @Bean(name = "customerEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(MultitenantDataSource multitenantDataSource) {
        val localContainerEmfb = new LocalContainerEntityManagerFactoryBean();
        localContainerEmfb.setDataSource(multitenantDataSource);
        localContainerEmfb.setPackagesToScan("io.github.marcelothebuilder.datasourcepertenant.model.customer");

        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        localContainerEmfb.setJpaVendorAdapter(jpaVendorAdapter);


        Properties jpaProperties = new Properties();
        jpaProperties.setProperty(Environment.HBM2DDL_AUTO, "create-drop");
        jpaProperties.setProperty(Environment.DIALECT, "org.hibernate.dialect.H2Dialect");
        jpaProperties.setProperty(Environment.SHOW_SQL, "true");
        localContainerEmfb.setJpaProperties(jpaProperties);

        return localContainerEmfb;
    }

    @Bean(name = "customerTransactionManager")
    public PlatformTransactionManager platformTransactionManager(
            @Qualifier("customerEntityManagerFactory")
                    LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean) {
        val tm = new JpaTransactionManager();
        tm.setEntityManagerFactory(localContainerEntityManagerFactoryBean.getObject());
        return tm;
    }
}
