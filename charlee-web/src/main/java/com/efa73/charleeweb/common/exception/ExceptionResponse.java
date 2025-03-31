package com.efa73.charleeweb.common.exception;

import org.springframework.lang.Nullable;

public record ExceptionResponse<T>(
        @Nullable T message
) {
    public static <T> ExceptionResponse<T> of(T message) {
        return new ExceptionResponse<>(message);
    }
}
