package com.onandon.cafe.cafekioskapi.error;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.FileNotFoundException;
import java.nio.file.NoSuchFileException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException{

    @ExceptionHandler({ CustomException.class })
    protected ResponseEntity<ErrorResponse> customExceptionHandler(CustomException e){
        log.error("ERROR!  :  "+   e.getErrorCode().message,e);
        return ErrorResponse.toErrorResponse(e.getErrorCode());
    }
    @ExceptionHandler({ NoSuchFileException.class })
    protected ResponseEntity<ErrorResponse> noSuchFileExceptionHandler(NoSuchFileException e){
        log.error("ERROR! : "+e.getMessage()+ErrorCode.NO_SUCH_FILE_ERROR.message,e);
        return ErrorResponse.toErrorResponse(ErrorCode.SERVER_ERROR);
    }

    @ExceptionHandler({ FileNotFoundException.class })
    protected ResponseEntity<ErrorResponse> fileNotFoundExceptionHandler(FileNotFoundException e){
        log.error("ERROR! : "+e.getMessage()+ErrorCode.FILE_NOT_FOUND_ERROR.message,e);
        return ErrorResponse.toErrorResponse(ErrorCode.FILE_NOT_FOUND_ERROR);
    }

    @ExceptionHandler({ BindException.class })
    public ResponseEntity<ErrorResponse> bindExceptionHandler(BindException e){
        log.error("ERROR! : ",e.getMessage());
        return ErrorResponse.toErrorResponse(ErrorCode.BIND_ERROR,e);
    }

    @ExceptionHandler({ Exception.class })
    protected ResponseEntity<ErrorResponse> serverErrorHandler(Exception e){
        log.error("ERROR! : "+e.getMessage()+ErrorCode.SERVER_ERROR.message);
        return ErrorResponse.toErrorResponse(ErrorCode.SERVER_ERROR);
    }
}
