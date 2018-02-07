package io.github.marcelothebuilder.datasourcepertenant.persistence.tenancy.impl;

import io.github.marcelothebuilder.datasourcepertenant.model.common.Company;

import javax.sql.DataSource;

public interface CompanyDataSourceInitializer {
    DataSource initialize(Company company);
}
