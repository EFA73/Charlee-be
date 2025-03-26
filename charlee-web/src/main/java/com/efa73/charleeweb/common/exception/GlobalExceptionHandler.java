package com.efa73.charleeweb.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {CustomException.class})
    protected ResponseEntity<ExceptionResponse> handleCustomException(CustomException e) {
        log.error(e.getMessage());
        ExceptionResponse<String> response = new ExceptionResponse<>(
                e.getCode().value(),
                e.getCode(),
                e.getMessage()
        );
        return ResponseEntity
                .status(e.getCode())
                .body(response);
    }
}
