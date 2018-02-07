package io.github.marcelothebuilder.datasourcepertenant.persistence.tenancy.impl;

import io.github.marcelothebuilder.datasourcepertenant.persistence.tenancy.TenantDataSourceInitializedEvent;
import lombok.AllArgsConstructor;

import javax.sql.DataSource;
import java.util.Optional;

@AllArgsConstructor
public class TenantDataSourceInitializedEventImpl implements TenantDataSourceInitializedEvent {
    private DataSource dataSource;

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }

    @Override
    public Optional<Object> getTenant() {
        return Optional.empty();
    }
}
