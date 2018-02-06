package io.github.marcelothebuilder.datasourcepertenant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration
@EnableWebSecurity(debug = true)
@EnableTransactionManagement
public class Application {
    public static void main(String[] main) {
        SpringApplication.run(Application.class, main);
    }
}
