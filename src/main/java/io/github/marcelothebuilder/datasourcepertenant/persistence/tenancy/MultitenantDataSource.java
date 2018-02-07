package io.github.marcelothebuilder.datasourcepertenant.persistence.tenancy;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public interface MultitenantDataSource extends DataSource {
    @Override
    Connection getConnection() throws SQLException;

    @Override
    Connection getConnection(String username, String password) throws SQLException;
}
