package com.example.resourceservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(final OAuth2ClientContext oAuth2ClientContext,
                                     final OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails) {

        final RestTemplate restTemplate = new OAuth2RestTemplate(oAuth2ProtectedResourceDetails, oAuth2ClientContext);
        final List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        interceptors.add(new TrackingInterceptor());
        return restTemplate;
    }
}
