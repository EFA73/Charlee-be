package com.efa73.charleeweb.common.exception;

public interface CharleeErrorCode {
    Integer getHttpStatus();

    String getMessage();
}
