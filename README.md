# Datasource Per Tenant
> Proof-of-concept: can we can dynamically provide datasources for different customers?

## How does this work?

The provided code has the minimum possible implementation for it to work.

Using Spring Data, we have 3 entities: **Company**, **User** and **Addresss**.

**Company** and **User** belongs to the **main (central) database**. We need a central database with the minimum amount of info to authenticate the user and bind him to a tenant (in this case, Company).

**Address** is just a dummy entity with a single `id` attribute. It belongs to **a tenant specific database**. 

Which database? The magic starts here.

After being authenticated through OAuth2 with Spring Security, when using any repository created under `io.github.marcelothebuilder.datasourcepertenant.repository.customer`, the DataSource used during the request will be decided by:

* Querying the user tenant info;
* Obtaining the tenant database credentials (url, username, password);
* Providing the DataSource bean based on those credentials.

## Benefits

* No need to keep a set of pre-defined database connection info;
* No need to be tied to a single database vendor (if an orm is used, the dialect can be dynamic);
* No need to re-deploy when a new customer comes in, just add it's credentials to the main database either by hand or automatically.


## Usage
### Create your own DataSource selection strategy

Implement `io.github.marcelothebuilder.datasourcepertenant.persistence.tenancy.MultitenantDataSource` as a primary bean.

In this example, the implementation is done at `io.github.marcelothebuilder.datasourcepertenant.persistence.tenancy.impl.MultitenantDataSourceImpl` which delegates the datasource creation strategy to a `io.github.marcelothebuilder.datasourcepertenant.persistence.tenancy.impl.DataSourceTenantFactory` bean. The `DataSourceTenantFactory` bean is not required, but it makes the code design easier. Our main logic is defined in `io.github.marcelothebuilder.datasourcepertenant.persistence.tenancy.impl.CentralDatabaseDataSourceTenantFactory`.


## License

Distributed under the GNU GENERAL PUBLIC LICENSE Version 3 license. See ``LICENSE`` for more information.
