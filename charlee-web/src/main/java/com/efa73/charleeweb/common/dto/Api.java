package com.efa73.charleeweb.common.dto;

import org.springframework.lang.Nullable;

//TODO: 질문 - 공통 응답 처리
public record Api<T>(
        @Nullable T data
) {
    public static <T> Api<T> of(T data) {
        return new Api<>(data);
    }
}
