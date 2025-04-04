package com.efa73.charleeweb.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private final String secretKey;
    private final AccessTokenProperties access;
    private final RefreshTokenProperties refresh;

    @Getter
    @RequiredArgsConstructor
    public static class AccessTokenProperties {
        private final Long expiration;
        private final String header;
    }

    @Getter
    @RequiredArgsConstructor
    public static class RefreshTokenProperties {
        private final Long expiration;
        private final String header;
    }
}
