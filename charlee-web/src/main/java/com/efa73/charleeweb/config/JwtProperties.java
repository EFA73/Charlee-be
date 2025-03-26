package com.efa73.charleeweb.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private String secretKey;
    private AccessTokenProperties access;
    private RefreshTokenProperties refresh;

    @Getter
    public static class AccessTokenProperties {
        private Long expiration;
        private String header;
    }

    @Getter
    public class RefreshTokenProperties {
        private Long expiration;
        private String header;
    }
}
