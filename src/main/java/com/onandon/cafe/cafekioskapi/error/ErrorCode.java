package com.onandon.cafe.cafekioskapi.error;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.nio.file.NoSuchFileException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Getter
public enum ErrorCode {

    // ALL
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"A00", " 서버 에러가 발생했습니다."),
    BAD_REQUEST_ERROR(HttpStatus.BAD_REQUEST,"A01", " 잘못된 요청입니다."),

    // FILE
    NO_SUCH_FILE_ERROR(HttpStatus.NOT_FOUND,"FILE_ERROR_00"," 파일을 찾을 수 없습니다."),
    FILE_NOT_FOUND_ERROR(HttpStatus.BAD_GATEWAY,"FILE_ERROR_01", " 지정된 파일을 찾을 수 없습니다."),

    // VALIDATION
    BIND_ERROR(HttpStatus.BAD_REQUEST,"VA00",""),
    // security
    UNAUTHORIZATION_CODE(HttpStatus.UNAUTHORIZED,"SECRUTY_00"," 허용되지않는 접근입니다.");
    
    private final HttpStatus status;
    private final String code;
    protected String message;

    ErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
