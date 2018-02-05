package io.github.marcelothebuilder.datasourcepertenant.repository.config;

import io.github.marcelothebuilder.datasourcepertenant.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
