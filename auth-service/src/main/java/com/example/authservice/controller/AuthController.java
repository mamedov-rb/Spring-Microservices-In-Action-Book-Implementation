package com.example.authservice.controller;

import org.springframework.http.MediaType;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class AuthController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public OAuth2Authentication user(final OAuth2Authentication user) {
        return user;
    }
}
