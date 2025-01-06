package org.example.todo.common;

import org.example.todo.exception.ErrorCode;

public record ErrorResponse(String errorCode, String message) {

    public static ErrorResponse of(ErrorCode errorCode, String message) {
        return new ErrorResponse(errorCode.getCode(), message != null ? message : errorCode.getMessage());
    }
}
