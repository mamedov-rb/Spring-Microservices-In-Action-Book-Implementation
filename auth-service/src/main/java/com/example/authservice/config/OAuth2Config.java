package com.example.authservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

    public static final String CLIENT_ID = "client-application-web";
    public static final String CLIENT_SECRET = "123qwe";
    public static final String PASSWORD_GRANT_TYPE = "password";
    public static final String REFRESH_TOKEN_GRANT_TYPE = "refresh_token";
    public static final String CLIENT_CREDENTIALS_GRANT_TYPE = "client_credentials";
    public static final String WEBCLIENT_SCOPE = "web-client";
    public static final String MOBILE_CLIENT_SCOPE = "mobile-client";

    private final PasswordEncoder passwordEncoder;

    private final UserDetailsService userDetailsService;

    private final AuthenticationManager authenticationManager;

    private final TokenStore tokenStore;

    private final JwtAccessTokenConverter jwtAccessTokenConverter;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory()
                .withClient(CLIENT_ID)
                .secret(passwordEncoder.encode(CLIENT_SECRET))
                .authorizedGrantTypes(
                        PASSWORD_GRANT_TYPE,
                        REFRESH_TOKEN_GRANT_TYPE,
                        CLIENT_CREDENTIALS_GRANT_TYPE
                )
                .scopes(WEBCLIENT_SCOPE, MOBILE_CLIENT_SCOPE);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        new TokenEnhancerChain()
                .setTokenEnhancers(List.of(jwtAccessTokenConverter));
        endpoints
                .tokenStore(tokenStore)
                .accessTokenConverter(jwtAccessTokenConverter)
                .userDetailsService(userDetailsService)
                .authenticationManager(authenticationManager);
    }
}
