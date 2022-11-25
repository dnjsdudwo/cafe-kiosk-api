package com.onandon.cafe.cafekioskapi.api;

import com.onandon.cafe.cafekioskapi.error.CustomException;
import com.onandon.cafe.cafekioskapi.error.ErrorCode;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ErrorAPIController {


    @GetMapping("/error/server")
    public ResponseEntity serverError() throws Exception {
        throw new CustomException(ErrorCode.NO_SUCH_FILE_ERROR);
    }

    @GetMapping("/error/file")
    public ResponseEntity fileError() throws Exception {
        throw new CustomException("TEST",ErrorCode.NO_SUCH_FILE_ERROR);
    }

    @GetMapping("/error/custom")
    public ResponseEntity customError() throws Exception {

        throw new CustomException(ErrorCode.NO_SUCH_FILE_ERROR);
    }
}
