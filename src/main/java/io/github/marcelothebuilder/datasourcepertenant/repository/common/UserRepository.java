package io.github.marcelothebuilder.datasourcepertenant.repository.common;

import io.github.marcelothebuilder.datasourcepertenant.model.common.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
