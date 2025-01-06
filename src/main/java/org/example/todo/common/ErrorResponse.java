package org.example.todo.common;

import org.example.todo.exception.ErrorCode;

public record ErrorResponse(String errorCode, String message, String detail) {

    public static ErrorResponse of(ErrorCode errorCode, String detail) {
        return new ErrorResponse(errorCode.getCode(), errorCode.getMessage(), detail);
    }
}
