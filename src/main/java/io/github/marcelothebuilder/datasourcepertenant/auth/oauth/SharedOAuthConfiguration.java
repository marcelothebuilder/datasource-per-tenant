package io.github.marcelothebuilder.datasourcepertenant.auth.oauth;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class SharedOAuthConfiguration {

    private AuthenticationManager authenticationManager;

    private JwtConfiguration jwtConfiguration;

    @Bean
    @Primary
    public TokenStore jwtTokenStore(JwtAccessTokenConverter jwtAccessTokenConverter) {
        return new JwtTokenStore(jwtAccessTokenConverter);
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        val converter= new JwtAccessTokenConverter();
        converter.setSigningKey(jwtConfiguration.getSigningKey());
        return converter;
    }

    @Bean
    public DefaultTokenServices authorizationServerTokenServices(TokenStore tokenStore) {
        val tokenServices = new DefaultTokenServices();
        tokenServices.setAuthenticationManager(authenticationManager);
        tokenServices.setTokenStore(tokenStore);
        tokenServices.setSupportRefreshToken(true);

        return tokenServices;
    }
}
