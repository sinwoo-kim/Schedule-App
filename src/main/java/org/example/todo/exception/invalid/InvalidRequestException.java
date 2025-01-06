package org.example.todo.exception.invalid;

import org.example.todo.exception.ErrorCode;
import org.example.todo.exception.common.BusinessException;

public class InvalidRequestException extends BusinessException {

    public InvalidRequestException(String message) {
        super(ErrorCode.NOT_FOUND, message);
    }

}
