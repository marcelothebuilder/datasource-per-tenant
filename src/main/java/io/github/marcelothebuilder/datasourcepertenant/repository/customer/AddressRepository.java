package io.github.marcelothebuilder.datasourcepertenant.repository.customer;

import io.github.marcelothebuilder.datasourcepertenant.model.customer.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}
