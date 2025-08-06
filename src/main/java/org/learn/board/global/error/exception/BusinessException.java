package org.learn.board.global.error.exception;

import lombok.Getter;
import org.learn.board.global.error.ErrorCode;

@Getter
public class BusinessException extends RuntimeException{

    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
