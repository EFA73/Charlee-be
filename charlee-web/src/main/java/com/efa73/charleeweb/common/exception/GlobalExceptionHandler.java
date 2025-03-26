package com.efa73.charleeweb.common.exception;

import com.efa73.charleeweb.common.dto.ExceptionResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 코드상에서 직접 발생시키는 예외 처리
     */
    @ExceptionHandler(CharleeException.class)
    protected ResponseEntity<?> handleAlreadyExistsException(CharleeException e) {
        log.error(e.getMessage());

        return sendErrorResponse(e.getHttpStatus(), e.getMessage());
    }

    /**
     * 그 외 모든 예외는 500 에러로 처리
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<?> unhandledException(Exception e) {
        log.error(e.getMessage());

        HttpStatus internalServerError = HttpStatus.INTERNAL_SERVER_ERROR;
        return sendErrorResponse(internalServerError.value(), internalServerError.getReasonPhrase());
    }

    private ResponseEntity<?> sendErrorResponse(int httpStatus, String message) {
        return ResponseEntity
                .status(httpStatus)
                .body(ExceptionResponse.of(message));
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
