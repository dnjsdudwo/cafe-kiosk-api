package com.onandon.cafe.cafekioskapi.api;


import com.onandon.cafe.cafekioskapi.service.coffee.FileAPIService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
public class FileAPIController {

    private final FileAPIService FileAPIServiceImpl;

    @PostMapping("/file/upload")
    public ResponseEntity<String> fileUpload(MultipartFile img_file) throws Exception {
        return FileAPIServiceImpl.fileUpload(img_file);
    }

}
