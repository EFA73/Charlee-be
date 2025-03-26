package com.efa73.charleeweb.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CustomErrorCode {
    EMAIL_ALREADY_EXISTS(HttpStatus.CONFLICT, "Email already exists."),
    EMPTY_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "Access token is empty.");

    private final HttpStatus httpStatus;
    private final String message;
}
