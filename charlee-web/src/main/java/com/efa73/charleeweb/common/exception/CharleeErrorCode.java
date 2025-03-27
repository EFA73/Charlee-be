package com.efa73.charleeweb.common.exception;

public interface CharleeErrorCode {
    int getHttpStatus();

    String getMessage();
}
