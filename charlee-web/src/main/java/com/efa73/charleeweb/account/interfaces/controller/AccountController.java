package com.efa73.charleeweb.account.interfaces.controller;

import com.efa73.charleeweb.account.domain.entity.Account;
import com.efa73.charleeweb.account.domain.service.AccountService;
import com.efa73.charleeweb.account.interfaces.dto.RegisterRequest;
import com.efa73.charleeweb.account.interfaces.dto.RegisterResponse;
import com.efa73.charleeweb.common.Api;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<Api<RegisterResponse>> registerUser(
            @RequestBody RegisterRequest userRegisterDto
    ) {
//        Long accountId = accountService.registerAccount(userRegisterDto);
        Account account = accountService.registerAccount(userRegisterDto);
        Api<RegisterResponse> userRegisterResponseApi = Api.of(RegisterResponse.of(account.getId()));
        return ResponseEntity.created(URI.create("/api/user/" + account.getId())).body(userRegisterResponseApi);
    }
}
