package io.github.marcelothebuilder.datasourcepertenant.persistence;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("app.datasources.common")
@Data
public class PersistenceCommonParameters {
    private String url;
    private String username;
    private String password;
    private String driverClassName;
}