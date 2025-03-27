package com.efa73.charleeweb.util;

import com.efa73.charleeweb.common.exception.CharleeException;
import com.efa73.charleeweb.common.exception.CommonErrorCode;
import com.efa73.charleeweb.user.domain.entity.User;
import com.efa73.charleeweb.user.domain.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Map<String, List<String>> NO_CHECK_METHODS = Map.of(
            "/api/login", List.of("POST"),
            "/api/register", List.of("POST"),
            "/swagger-ui", List.of("GET"),
            "/v3/api-docs", List.of("GET")
    );

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper(); //TODO: 뭐지

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String method = request.getMethod();

        // jwt 검증하지 않는 요청 처리
        if (NO_CHECK_METHODS.entrySet().stream()
                .anyMatch(entry -> requestURI.startsWith(entry.getKey()) && entry.getValue().contains(method))) {
            filterChain.doFilter(request, response);
            return;
        }

        String accessToken = jwtTokenProvider.extractAccessToken(request)
                .orElse(null);

        try {
            if (accessToken != null && jwtTokenProvider.isTokenValid(accessToken)) {
                checkAccessTokenAndAuthentication(request, response, filterChain);
            }
            if (accessToken == null) {
                throw new CharleeException(CommonErrorCode.EMPTY_ACCESS_TOKEN);
            }
        } catch (CharleeException e) {
            log.info("Token is invalid or expired");
            handleInvalidTokenException(response, e);
        }

//        String refreshToken = jwtTokenProvider.extractRefreshToken(request)
//                .filter(jwtTokenProvider::isTokenValid)
//                .orElse(null);
//
//        // Refresh token이 요청 헤더에 포함되어 있을 경우 access token 재발급
//        if (refreshToken != null) {
//            checkRefreshTokenAndReissueAccessToken(response, refreshToken);
//            return;
//        }
//
//        if (refreshToken == null) {
//            checkAccessTokenAndAuthentication(request, response, filterChain);
//        }
    }

    private void handleInvalidTokenException(HttpServletResponse response, CharleeException e)
            throws IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

//        ExceptionResponse errorResponse = ExceptionResponse.of("Invalid or expired token");
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.writeValue(response.getWriter(), errorResponse);
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                "Invalid or expired token"); // TODO: invalid token 테스트 후 수정 필요
    }

    /**
     * AccessToken 검증 후 권한 정보 저장 및 인증
     */
    private void checkAccessTokenAndAuthentication(HttpServletRequest request, HttpServletResponse response,
                                                   FilterChain filterChain) throws ServletException, IOException {
        log.info("call checkAccessTokenAndAuthentication()");
        jwtTokenProvider.extractAccessToken(request)
//                .filter(jwtTokenProvider::isTokenValid)
                .ifPresent(accessToken -> {
                    jwtTokenProvider.extractEmail(accessToken)
                            .ifPresent(email -> userRepository.findByEmail(email)
                                    .ifPresent(this::saveAuthentication)
                            );
                    jwtTokenProvider.extractUserId(accessToken)
                            .ifPresent(userId -> log.info("access token userId: {}", userId));
                });
        filterChain.doFilter(request, response);
    }

    /**
     * AccessToken 인증 성공 이후, 해당 객체를 SecurityCntextHolder에 담아 인증 처리
     */
    private void saveAuthentication(User user) {
        String password = user.getPassword();

        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(password)
                .roles(user.getRole().name())
                .build();

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, authoritiesMapper.mapAuthorities(userDetails.getAuthorities()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    /**
     * Password 랜덤 생성
     */
    private String generateRandomPassword(int length) {
        final String CHAR_SET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@#$%^&*()-_=+[]{}|;:,.<>?";
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder password = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = secureRandom.nextInt(CHAR_SET.length());
            password.append(CHAR_SET.charAt(index));
        }
        return password.toString();
    }

//    /**
//     * RefreshToken 검증 후, AccessToken, RefreshToken 재발급
//     */
//    private void checkRefreshTokenAndReissueAccessToken(HttpServletResponse response, String refreshToken) {
//        userRepository.findByRefreshToken(refreshToken)
//                .ifPresent()
//    }
}
