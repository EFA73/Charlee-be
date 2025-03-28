package com.efa73.charleeweb.account.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.efa73.charleeweb.config.JwtProperties;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Optional;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;

    private static final String ACCESS_SUBJECT = "AccessToken";
    private static final String REFRESH_SUBJECT = "RefreshToken";
    private static final String EMAIL_CLAIM = "email";
    private static final String ROLE_CLAIM = "role";
    private static final String USER_ID_CLAIM = "userId";
    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String EMPTY_STRING = "";
    private static final String MESSAGE = "Access token is invalid."; //TODO: CustomErrorCode

    private String secretKey;
    private Long accessTokenExpTime;
    private Long refreshTokenExpTime;
    private String accessHeader;
    private String refreshHeader;

    @PostConstruct
    private void init() {
        this.secretKey = jwtProperties.getSecretKey();
        this.accessTokenExpTime = jwtProperties.getAccess().getExpiration();
        this.refreshTokenExpTime = jwtProperties.getRefresh().getExpiration();
        this.accessHeader = jwtProperties.getAccess().getHeader();
        this.refreshHeader = jwtProperties.getRefresh().getHeader();
    }

    /**
     * AccessToken 생성
     */
    public String createAccessToken(String email, String role, Long userId) {
        Date now = new Date();
        Date expireAt = new Date(now.getTime() + accessTokenExpTime);

        return JWT.create()
                .withSubject(ACCESS_SUBJECT)
                .withExpiresAt(expireAt)
                .withClaim(EMAIL_CLAIM, email)
                .withClaim(ROLE_CLAIM, role)
                .withClaim(USER_ID_CLAIM, userId)
                .sign(Algorithm.HMAC512(secretKey));
    }

    /**
     * RefreshToken 생성
     */
    public String createRefreshToken() {
        Date now = new Date();
        Date expireAt = new Date(now.getTime() + accessTokenExpTime);

        return JWT.create()
                .withSubject(REFRESH_SUBJECT)
                .withExpiresAt(expireAt)
                .sign(Algorithm.HMAC512(secretKey));
    }

    /**
     * Response header에 AccessToken 넣기
     */
    public void addAccessTokenToResponse(HttpServletResponse response, String accessToken) {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setHeader(accessHeader, accessToken);
    }

    /**
     * Response header에 AccessToken, RefreshToken 넣기
     */
    public void addAccessAndRefreshTokenToResponse(HttpServletResponse response, String accessToken) {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setHeader(accessHeader, accessToken);
        response.setHeader(refreshHeader, refreshHeader);
    }

    /**
     * Header에서 AccessToken 추출
     */
    public Optional<String> extractAccessToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(accessHeader))
                .filter(accessToken -> accessToken.startsWith(TOKEN_PREFIX))
                .map(accessToken -> accessToken.replace(TOKEN_PREFIX, EMPTY_STRING));
    }

    /**
     * Header에서 RefreshToken 추출
     */
    public Optional<String> extractRefreshToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(refreshHeader))
                .filter(refreshToken -> refreshToken.startsWith(TOKEN_PREFIX))
                .map(refreshToken -> refreshToken.replace(TOKEN_PREFIX, EMPTY_STRING));
    }

    /**
     * AccessToken에서 email 추출
     */
    public Optional<String> extractEmail(String accessToken) {
        return extractClaim(accessToken, EMAIL_CLAIM, Claim::asString);
    }

    /**
     * AccessToken에서 role 추출
     */
    public Optional<String> extractRole(String accessToken) {
        return extractClaim(accessToken, ROLE_CLAIM, Claim::asString);
    }

    /**
     * AccessToken에서 userId 추출
     */
    public Optional<Long> extractUserId(String accessToken) {
        return extractClaim(accessToken, USER_ID_CLAIM, Claim::asLong);
    }

    /**
     * 토큰 유효성 검증
     */
    public boolean isTokenValid(String token) {
        try {
            JWT.require(Algorithm.HMAC512(secretKey))
                    .build()
                    .verify(token);
            return true;
        } catch (JWTVerificationException e) {
            log.error(MESSAGE);
            return false;
        }
    }

    private <T> Optional<T> extractClaim(String accessToken, String claim, Function<Claim, T> mapper) {
        try {
            return Optional.ofNullable(
                    mapper.apply(
                            JWT.require(Algorithm.HMAC512(secretKey))
                                    .build()
                                    .verify(accessToken)
                                    .getClaim(claim)
                    )
            );
        } catch (JWTVerificationException e) {
            log.error(MESSAGE);
            return Optional.empty();
        }
    }
}
