package com.efa73.charleeweb.common.exception;

import lombok.Getter;

@Getter
public class CharleeException extends RuntimeException {
    private final int httpStatus;

    public CharleeException(CharleeErrorCode charleeErrorCode) {
        super(charleeErrorCode.getMessage());
        this.httpStatus = charleeErrorCode.getHttpStatus();
    }
}
