package com.onandon.cafe.cafekioskapi.error;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Getter
@Builder
public  class ErrorResponse {
    private final LocalDateTime timeStamp = LocalDateTime.now();
    private int status;
    private String error;
    private String code;
    private String message;


    public static ResponseEntity<ErrorResponse> toErrorResponse(ErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(ErrorResponse.builder()
                        .code(errorCode.getCode())
                        .status(errorCode.getStatus().value())
                        .error(errorCode.getStatus().name())
                        .message(errorCode.message)
                        .build()
                );

    }

    public static ResponseEntity<ErrorResponse> toErrorResponse(ErrorCode errorCode, BindException e) {
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(ErrorResponse.builder()
                .code(errorCode.getCode())
                .status(errorCode.getStatus().value())
                .error(errorCode.getStatus().name())
                .message((e.getFieldErrors()
                        .stream()
                        .map(er -> er.getDefaultMessage())
                        .collect(Collectors.joining("\n"))))
                .build()
                );
    }

}
