package io.github.marcelothebuilder.datasourcepertenant.persistence.tenancy.impl;

import io.github.marcelothebuilder.datasourcepertenant.persistence.tenancy.MultitenantDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.AbstractDataSource;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

@Component
@Slf4j
public class MultitenantDataSourceImpl extends AbstractDataSource implements MultitenantDataSource {
    @Autowired
    private DataSourceTenantFactory dataSourceTenantFactory;

    @Override
    public Connection getConnection() throws SQLException {
        log.info("getConntetion");
        return dataSourceTenantFactory.getDataSource().getConnection();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        log.info("getConntetion with username pass");
        return null;
    }
}
