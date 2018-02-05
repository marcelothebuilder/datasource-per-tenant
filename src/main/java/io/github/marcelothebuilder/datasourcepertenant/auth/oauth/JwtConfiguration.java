package io.github.marcelothebuilder.datasourcepertenant.auth.oauth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@ConfigurationProperties("jwt")
public class JwtConfiguration {
    private String signingKey;
}
