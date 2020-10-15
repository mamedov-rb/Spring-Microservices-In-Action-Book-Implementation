package com.example.resourceservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;

@Slf4j
@RestController
@RequestMapping("/resource")
@RequiredArgsConstructor
public class ResourceController {

    private final static String URL = "http://resource-service-second/resource";

    private final Environment environment;

    private final RestTemplate restTemplate;

    @GetMapping
    public String info() {
        final String host = InetAddress.getLoopbackAddress().getHostAddress();
        final String port = environment.getProperty("server.port");
        final ResponseEntity<String> exchange = restTemplate.exchange(URL, HttpMethod.GET, null, String.class);
        final String format = String.format("Service-1 info: host '%s', port '%s';\nService-2 info: %s", host, port, exchange.getBody());
        log.info(format);
        return format;
    }

}
