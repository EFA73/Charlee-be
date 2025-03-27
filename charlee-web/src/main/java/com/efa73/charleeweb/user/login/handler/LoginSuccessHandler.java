package com.efa73.charleeweb.user.login.handler;

import com.efa73.charleeweb.common.dto.Api;
import com.efa73.charleeweb.user.domain.repository.UserRepository;
import com.efa73.charleeweb.user.interfaces.dto.LoginResponse;
import com.efa73.charleeweb.util.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Slf4j
@RequiredArgsConstructor
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
//        super.onAuthenticationSuccess(request, response, authentication);

        String email = extractEmail(authentication);
        userRepository.findByEmail(email)
                .ifPresent(user -> {
                    String accessToken = jwtTokenProvider.createAccessToken(
                            email,
                            user.getRole().toString(),
                            user.getId()
                    );
                    // 헤더에 토큰 설정
                    jwtTokenProvider.addAccessAndRefreshTokenToResponse(response, accessToken);

                    ResponseEntity<Api<LoginResponse>> loginResponse = ResponseEntity.ok()
                            .body(Api.of(LoginResponse.of(email)));
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    try {
                        objectMapper.writeValue(
                                response.getWriter(),
                                loginResponse.getBody()
                        );
                    } catch (IOException e) {
                        log.error("login response body error", e);
                    }
                });
    }


    private String extractEmail(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getUsername();
    }
}
