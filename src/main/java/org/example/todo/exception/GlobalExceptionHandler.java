package org.example.todo.exception;

import lombok.extern.slf4j.Slf4j;
import org.example.todo.common.ErrorResponse;
import org.example.todo.exception.common.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessExeption(BusinessException ex) {
        log.error("BusinessException 발생: {}", ex.getMessage(), ex);
        ErrorCode errorCode = ex.getErrorCode();
        ErrorResponse errorResponse = ErrorResponse.of(errorCode, ex.getMessage());
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(errorResponse);
    }

}
