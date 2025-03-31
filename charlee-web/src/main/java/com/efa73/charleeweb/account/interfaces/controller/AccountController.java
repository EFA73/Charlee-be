package com.efa73.charleeweb.account.interfaces.controller;

import com.efa73.charleeweb.account.domain.service.AccountService;
import com.efa73.charleeweb.account.interfaces.dto.RegisterRequest;
import com.efa73.charleeweb.account.interfaces.dto.RegisterResponse;
import com.efa73.charleeweb.common.dto.Api;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<Api<RegisterResponse>> registerUser(
            @RequestBody RegisterRequest userRegisterDto
    ) {
        Long userId = accountService.registerUser(userRegisterDto);
        Api<RegisterResponse> userRegisterResponseApi = Api.of(RegisterResponse.of(userId));
        return ResponseEntity.created(URI.create("/api/user/" + userId)).body(userRegisterResponseApi);
    }
}
