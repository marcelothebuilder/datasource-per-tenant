package io.github.marcelothebuilder.datasourcepertenant.persistence;

import io.github.marcelothebuilder.datasourcepertenant.model.common.User;
import io.github.marcelothebuilder.datasourcepertenant.repository.common.CompanyRepository;
import lombok.val;
import org.hibernate.cfg.Environment;
import org.hibernate.dialect.H2Dialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackageClasses = CompanyRepository.class,
        transactionManagerRef = "commonTransactionManager",
        entityManagerFactoryRef = "commonEntityManagerFactory")
public class PersistenceCommonConfiguration {

    @Autowired
    private PersistenceCommonParameters configuration;

    @Bean(name = "dataSource")
    @Primary
    public DataSource dataSource() {
        val ds = new DriverManagerDataSource();
        ds.setUsername(configuration.getUsername());
        ds.setPassword(configuration.getPassword());
        ds.setUrl(configuration.getUrl());
        ds.setDriverClassName(configuration.getDriverClassName());
        return ds;
    }

    @Bean(name = "commonEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean() {
        val localContainerEmfb = new LocalContainerEntityManagerFactoryBean();
        localContainerEmfb.setDataSource(dataSource());
        localContainerEmfb.setPackagesToScan(User.class.getPackage().getName());

        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        localContainerEmfb.setJpaVendorAdapter(jpaVendorAdapter);


        Properties jpaProperties = new Properties();
        jpaProperties.setProperty(Environment.HBM2DDL_AUTO, "create-drop");
        jpaProperties.setProperty(Environment.DIALECT, H2Dialect.class.getName());
        jpaProperties.setProperty(Environment.SHOW_SQL, Boolean.TRUE.toString());
        localContainerEmfb.setJpaProperties(jpaProperties);

        return localContainerEmfb;
    }

    @Bean(name = "commonTransactionManager")
    @Primary
    public PlatformTransactionManager platformTransactionManager() {
        val tm = new JpaTransactionManager();
        tm.setEntityManagerFactory(localContainerEntityManagerFactoryBean().getObject());
        return tm;
    }


}
