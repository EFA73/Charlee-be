package com.efa73.charleeweb.common.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public class CustomException extends RuntimeException {
    private final CustomErrorCode errorCode;

    public HttpStatus getCode() {
        return errorCode.getHttpStatus();
    }

    public String getMessage() {
        return errorCode.getMessage();
    }
}
