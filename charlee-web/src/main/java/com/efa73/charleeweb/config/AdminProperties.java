package com.efa73.charleeweb.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "admin")
public class AdminProperties {

    private final String name;
    private final String email;
    private final String password;
}
