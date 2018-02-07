package io.github.marcelothebuilder.datasourcepertenant.persistence.tenancy;


import javax.sql.DataSource;
import java.util.Optional;

/**
 * An event that represents a {@link DataSource} that was initialized for a specific tenant.
 */
public interface TenantDataSourceInitializedEvent {

    /**
     * @return The newly initialized datasource
     */
    DataSource getDataSource();

    /**
     * @return The tenant information. Providers may or not set this info, and any kind of object can be returned.
     */
    Optional<Object> getTenant();
}
