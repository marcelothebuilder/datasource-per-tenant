package io.github.marcelothebuilder.datasourcepertenant.persistence;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.AbstractDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

@Component
@Slf4j
public class MultitenantDataSource extends AbstractDataSource {

    @Override
    public Connection getConnection() throws SQLException {
        log.info("getConntetion");
        return null;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        log.info("getConntetion with username pass");
        return null;
    }
}
