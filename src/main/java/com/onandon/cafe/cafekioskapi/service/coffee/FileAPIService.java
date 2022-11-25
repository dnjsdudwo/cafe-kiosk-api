package com.onandon.cafe.cafekioskapi.service.coffee;


import com.onandon.cafe.cafekioskapi.error.CustomException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
public interface FileAPIService {
    ResponseEntity fileUpload(MultipartFile file) throws Exception;

    ResponseEntity fileMoveDir(String fileName) throws Exception;

}
