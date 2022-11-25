package com.onandon.cafe.cafekioskapi.error;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class CustomException extends RuntimeException {
    private final ErrorCode errorCode;

    public CustomException(String message, ErrorCode errorCode){
        super(message);
        errorCode.message = message;
        this.errorCode = errorCode;
    }
    public CustomException(ErrorCode errorCode){
        super(errorCode.message);
        this.errorCode = errorCode;
    }
}
