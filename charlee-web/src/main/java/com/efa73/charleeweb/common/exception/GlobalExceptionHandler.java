package com.efa73.charleeweb.common.exception;

import com.efa73.charleeweb.common.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {CustomException.class})
    protected Api<?> handleCustomException(CustomException e) {
        log.error(e.getMessage());
        return Api.error(e);
    }
}
