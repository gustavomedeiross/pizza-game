package com.unisul.utils;

public class StackException extends RuntimeException {
    private ErrorCode errorCode;

    public StackException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public enum ErrorCode {
        EMPTY
    }
}
