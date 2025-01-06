package org.example.todo.exception.data;

import org.example.todo.exception.ErrorCode;
import org.example.todo.exception.common.BusinessException;

public class DataAlreadyExistsException extends BusinessException {
    public DataAlreadyExistsException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
