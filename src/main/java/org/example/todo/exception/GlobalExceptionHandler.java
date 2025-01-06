package org.example.todo.exception;

import lombok.extern.slf4j.Slf4j;
import org.example.todo.common.ApiResponse;
import org.example.todo.common.ErrorResponse;
import org.example.todo.exception.common.BusinessException;
import org.example.todo.exception.data.DataAlreadyExistsException;
import org.springframework.http.HttpStatus;
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

    @ExceptionHandler(DataAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<?>> dataAlreadyExistsException(DataAlreadyExistsException ex) {
        ApiResponse<Object> errorResponse = ApiResponse.error(HttpStatus.CONFLICT, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

}
