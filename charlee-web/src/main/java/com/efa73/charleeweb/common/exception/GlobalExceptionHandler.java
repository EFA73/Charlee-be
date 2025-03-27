package com.efa73.charleeweb.common.exception;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {AlreadyExistsException.class})
    protected ResponseEntity<ExceptionResponse> handleAlreadyExistsException(AlreadyExistsException e) {
        log.error(e.getMessage());
        ExceptionResponse<String> response = ExceptionResponse.of(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(response);
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    protected ResponseEntity<ExceptionResponse> handleEntityNotFoundException(EntityNotFoundException e) {
        log.error(e.getMessage());
        ExceptionResponse<String> response = ExceptionResponse.of(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }
}
