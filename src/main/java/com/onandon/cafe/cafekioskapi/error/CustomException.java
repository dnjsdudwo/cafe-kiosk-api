package com.onandon.cafe.cafekioskapi.error;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

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
        Map<String,String> originMessage = saveMessage();
        String message = originMessage.get(errorCode.getCode());
        errorCode.message = message;
        this.errorCode = errorCode;
    }
    @PostConstruct
    private Map<String,String> saveMessage(){
        Map<String,String> messages = new HashMap<>();
        for (ErrorCode err : ErrorCode.values()) {
            messages.put(err.getCode(),err.getMessage());
        }
        return messages;
    }
}
