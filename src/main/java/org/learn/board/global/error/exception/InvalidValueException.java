package org.learn.board.global.error.exception;

import lombok.Getter;
import org.learn.board.global.error.ErrorCode;

@Getter
public class InvalidValueException extends BusinessException{

    public InvalidValueException(ErrorCode errorCode) {
        super(errorCode);
    }
}
