package com.example.authservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String USER_001 = "user-001";
    public static final String PASSWORD_USER_001 = "123asd";
    public static final String USER_002 = "user-002";
    public static final String PASSWORD_USER_002 = "123zxc";
    public static final String ROLE_USER = "USER";
    public static final String ROlE_ADMIN = "ADMIN";

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                    .withUser(USER_001)
                    .password(passwordEncoder().encode(PASSWORD_USER_001))
                    .roles(ROLE_USER, ROlE_ADMIN)
                .and()
                    .withUser(USER_002)
                    .password(passwordEncoder().encode(PASSWORD_USER_002))
                    .roles(ROLE_USER);
    }
}
