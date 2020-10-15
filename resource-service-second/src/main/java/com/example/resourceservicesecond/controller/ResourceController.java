package com.example.resourceservicesecond.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;

@Slf4j
@RestController
@RequestMapping("/resource")
@RequiredArgsConstructor
public class ResourceController {

    private final Environment environment;

    @GetMapping
    public String info() {
        final String host = InetAddress.getLoopbackAddress().getHostAddress();
        final String port = environment.getProperty("server.port");
        final String format = String.format("host '%s', port '%s'", host, port);
        log.info(format);
        return format;
    }

}
