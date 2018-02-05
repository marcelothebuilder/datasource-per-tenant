package io.github.marcelothebuilder.datasourcepertenant.repository.config;

import io.github.marcelothebuilder.datasourcepertenant.model.Company;
import org.springframework.data.repository.CrudRepository;


public interface CompanyRepository extends CrudRepository<Company, Long> {

}
