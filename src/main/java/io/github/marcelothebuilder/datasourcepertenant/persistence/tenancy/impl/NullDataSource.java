package io.github.marcelothebuilder.datasourcepertenant.persistence.tenancy.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.AbstractDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Does nothing, just logs methods invocation and returns nulls.
 */
@Slf4j
public class NullDataSource extends AbstractDataSource {

    @Override
    public Connection getConnection() throws SQLException {
        log.debug("NullDataSource Method invoked.");
        return null;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        log.debug("NullDataSource Method invoked.");
        return null;
    }
}
