package com.efa73.charleeweb.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.lang.Nullable;

public record Api<T>(
        int code,
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

//    public static <T> Api<T> error(final CustomException e) {
//        return new Api<>(status.value(), status, message);
//    }
}
