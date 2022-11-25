package com.onandon.cafe.cafekioskapi.service.coffee;


import ch.qos.logback.core.util.FileUtil;
import com.onandon.cafe.cafekioskapi.error.CustomException;
import com.onandon.cafe.cafekioskapi.error.ErrorCode;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;

@Service
public class FileAPIServiceImpl implements FileAPIService{
    @Value("${spring.servlet.tempLocation}")
    private String FILE_TMP_LOCATION;

    @Value("${spring.servlet.location}")
    private String FILE_LOCATION;

    @Override
    public ResponseEntity<String> fileUpload(MultipartFile file) throws Exception {
        String fileName = "";
        try {
            fileName = getImageFileUrl(file);
        }
        catch (FileNotFoundException e){
            throw new CustomException(ErrorCode.FILE_NOT_FOUND_ERROR);
        }

        return ResponseEntity.created(URI.create(fileName)).build();
    }

    @Override
    public ResponseEntity fileMoveDir(String fileName) throws Exception {
        try {
            Path oldFile = Paths.get(FILE_TMP_LOCATION+fileName+".jpg");
            Path newFile = Paths.get(FILE_LOCATION+fileName+".jpg");
            Files.move(oldFile,newFile);
        }
        catch (FileNotFoundException e){
            throw new CustomException(ErrorCode.FILE_NOT_FOUND_ERROR);
        }

        return ResponseEntity.ok().build();
    }




    public String getImageFileUrl(MultipartFile img_file) throws Exception{
        Long fileName = Calendar.getInstance().getTimeInMillis();
        String filePath = FILE_TMP_LOCATION+"img_"+fileName+".jpg";
        img_file.transferTo(new File(filePath));
        return "img_"+fileName;
    }
}
