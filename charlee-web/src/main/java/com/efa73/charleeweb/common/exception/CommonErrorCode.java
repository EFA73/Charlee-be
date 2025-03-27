package com.efa73.charleeweb.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CommonErrorCode implements CharleeErrorCode {
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "찾을 수 없는 리소스입니다."),
    EMAIL_ALREADY_EXISTS(HttpStatus.CONFLICT, "중복된 이메일입니다."),
    ;

    private final int httpStatus;
    private final String message;

    CommonErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus.value();
        this.message = message;
    }

    CommonErrorCode(int httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
