package com.efa73.charleeweb.common.exception;

import static com.efa73.charleeweb.common.exception.CustomErrorCode.EMAIL_ALREADY_EXISTS;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AlreadyExistsException extends RuntimeException {

    public AlreadyExistsException() {
        super(EMAIL_ALREADY_EXISTS.getMessage());
    }
}
