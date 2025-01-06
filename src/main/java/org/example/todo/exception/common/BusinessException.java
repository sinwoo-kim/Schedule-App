package org.example.todo.exception.common;

import lombok.Getter;
import org.example.todo.exception.ErrorCode;

@Getter
public class BusinessException extends RuntimeException {

    private final ErrorCode errorCode;

    // 상세 메시지를 포함하는 생성자
    public BusinessException(ErrorCode errorCode, String message) {
        super(message); // 상위 클래스의 message로 상세 메시지 사용
        this.errorCode = errorCode;
    }

}
