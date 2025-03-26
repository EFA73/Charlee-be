package com.efa73.charleeweb.common.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.lang.Nullable;

public record ExceptionResponse<T>(
        Integer code,
        HttpStatusCode status,
        @Nullable T body
) {
}
