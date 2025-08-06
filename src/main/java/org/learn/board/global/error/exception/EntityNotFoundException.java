package org.learn.board.global.error.exception;

import lombok.Getter;
import org.learn.board.global.error.ErrorCode;

@Getter
public class EntityNotFoundException extends BusinessException{

    public EntityNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
