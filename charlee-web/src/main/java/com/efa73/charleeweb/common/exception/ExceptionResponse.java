package com.efa73.charleeweb.common.exception;

import org.springframework.lang.Nullable;

public record ExceptionResponse<T>(
        @Nullable T message
) {
    public static ExceptionResponse of(String message) {
        return new ExceptionResponse(message);
    }
}
