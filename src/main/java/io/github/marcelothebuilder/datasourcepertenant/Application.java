package io.github.marcelothebuilder.datasourcepertenant;

import io.github.marcelothebuilder.datasourcepertenant.repository.config.CompanyRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = CompanyRepository.class)
@EnableTransactionManagement
public class Application {
    public static void main(String[] main) {
        SpringApplication.run(Application.class, main);
    }
}
