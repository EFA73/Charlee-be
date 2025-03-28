package com.efa73.charleeweb.account.interfaces.controller;

import com.efa73.charleeweb.common.dto.Api;
import com.efa73.charleeweb.account.domain.service.UserService;
import com.efa73.charleeweb.account.interfaces.dto.LoginRequest;
import com.efa73.charleeweb.account.interfaces.dto.UserRegisterRequest;
import com.efa73.charleeweb.account.interfaces.dto.UserRegisterResponse;
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
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Api<UserRegisterResponse>> registerUser(
            @RequestBody UserRegisterRequest userRegisterDto
    ) {
        Long userId = userService.registerUser(userRegisterDto);
        Api<UserRegisterResponse> userRegisterResponseApi = Api.of(UserRegisterResponse.of(userId));
        return ResponseEntity.created(URI.create("/user/" + userId)).body(userRegisterResponseApi);
    }

    @PostMapping("/login")
    public void loginUser(
            @RequestBody LoginRequest loginRequest
    ) {
    }
}
