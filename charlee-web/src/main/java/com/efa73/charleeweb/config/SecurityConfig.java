package com.efa73.charleeweb.config;

import com.efa73.charleeweb.user.domain.repository.UserRepository;
import com.efa73.charleeweb.user.jwt.JwtAuthenticationFilter;
import com.efa73.charleeweb.user.jwt.JwtTokenProvider;
import com.efa73.charleeweb.user.login.filter.CustomAuthenticationProcessingFilter;
import com.efa73.charleeweb.user.login.handler.LoginFailureHandler;
import com.efa73.charleeweb.user.login.handler.LoginSuccessHandler;
import com.efa73.charleeweb.user.login.service.CustomUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailsService customUserDetailsService;
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                                .requestMatchers("/api/register", "/api/login").permitAll()
//                        .anyRequest().authenticated() // TODO: 나중에 인증 필요로 변경
                                .anyRequest().permitAll()
                )
                .addFilterAfter(customAuthenticationProcessingFilter(), LogoutFilter.class)
                .addFilterBefore(jwtAuthenticationFilter(),
                        CustomAuthenticationProcessingFilter.class
                )
        ;
        return http.build();
    }

    @Bean
    public Filter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtTokenProvider, userRepository, objectMapper);
    }

    @Bean
    public Filter customAuthenticationProcessingFilter() {
        CustomAuthenticationProcessingFilter customAuthenticationProcessingFilter
                = new CustomAuthenticationProcessingFilter(objectMapper);
        customAuthenticationProcessingFilter.setAuthenticationManager(authenticationManager());
        customAuthenticationProcessingFilter.setAuthenticationSuccessHandler(loginSuccessHandler());
        customAuthenticationProcessingFilter.setAuthenticationFailureHandler(loginFailureHandler());
        return customAuthenticationProcessingFilter;
    }

    @Bean
    public LoginFailureHandler loginFailureHandler() {
        return new LoginFailureHandler(objectMapper);
    }

    @Bean
    public LoginSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler(jwtTokenProvider, userRepository, objectMapper);
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(customUserDetailsService);
        return new ProviderManager(provider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
