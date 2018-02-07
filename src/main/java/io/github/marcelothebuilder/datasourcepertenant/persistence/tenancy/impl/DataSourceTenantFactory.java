package io.github.marcelothebuilder.datasourcepertenant.persistence.tenancy.impl;

import javax.sql.DataSource;

public interface DataSourceTenantFactory {
    DataSource getDataSource();
}
