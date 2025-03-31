package com.efa73.charleeweb.account.login.filter;

import com.efa73.charleeweb.account.interfaces.dto.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

public class CustomAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private static final String DEFAULT_LOGIN_REQUEST_URL = "/api/login";

    private final ObjectMapper objectMapper;

    public CustomAuthenticationProcessingFilter(ObjectMapper objectMapper) {
        super(DEFAULT_LOGIN_REQUEST_URL);
        this.objectMapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        // 요청의 유효성 검사
        if (request.getContentType() == null) {
            throw new AuthenticationServiceException(
                    "Authentication Content-Type is null: " + request.getContentType());
        }

        LoginRequest loginRequest = objectMapper.readValue(request.getInputStream(), LoginRequest.class);

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                loginRequest.email(),
                loginRequest.password()
        );

        try {
            return this.getAuthenticationManager().authenticate(authRequest);
        } catch (AuthenticationException e) {
            throw e;
        }
    }
}
