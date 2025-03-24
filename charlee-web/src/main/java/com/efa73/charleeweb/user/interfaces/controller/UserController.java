package com.efa73.charleeweb.user.interfaces.controller;

import com.efa73.charleeweb.common.Api;
import com.efa73.charleeweb.user.domain.service.UserService;
import com.efa73.charleeweb.user.interfaces.dto.UserRegisterRequest;
import com.efa73.charleeweb.user.interfaces.dto.UserRegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/user")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public Api<UserRegisterResponse> registerUser(
            @RequestBody UserRegisterRequest userRegisterDto
    ) {
        Long userId = userService.registerUser(userRegisterDto);
        return Api.create(UserRegisterResponse.of(userId));
    }
}
