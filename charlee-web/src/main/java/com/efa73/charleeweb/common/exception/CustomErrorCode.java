package com.efa73.charleeweb.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CustomErrorCode {
    EMAIL_ALREADY_EXISTS(HttpStatus.CONFLICT, "Email already exists."),
    ENTITY_NOT_FOUND(HttpStatus.NOT_FOUND, "Entity not found."),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
