package com.efa73.charleeweb.common;

import com.efa73.charleeweb.common.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.lang.Nullable;

public record Api<T>(
        Integer code,
        HttpStatusCode status,
        @Nullable T body
) {

    public static <T> Api<T> ok(@Nullable final T body) {
        HttpStatus ok = HttpStatus.OK;
        return new Api<>(ok.value(), ok, body);
    }

    public static <T> Api<T> create(@Nullable final T body) {
        HttpStatus created = HttpStatus.CREATED;
        return new Api<>(created.value(), created, body);
    }

    public static <T> Api<String> error(final CustomException e) {
        return new Api<>(e.getCode().value(), e.getCode(), e.getMessage());
    }
}
