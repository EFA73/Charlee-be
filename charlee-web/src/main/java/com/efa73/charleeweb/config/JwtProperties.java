package com.efa73.charleeweb.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter //TODO: setter 안쓰는 방법 찾기
@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private String secretKey;
    private AccessTokenProperties access;
    private RefreshTokenProperties refresh;

    @Setter
    @Getter
    @NoArgsConstructor
    public static class AccessTokenProperties {
        private Long expiration;
        private String header;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    public static class RefreshTokenProperties {
        private Long expiration;
        private String header;
    }
}
