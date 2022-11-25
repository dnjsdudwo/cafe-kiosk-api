package com.onandon.cafe.cafekioskapi.scheduler;


import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Slf4j
@Component
public class FileScheduling {

    @Value("spring.servlet.tempLocation")
    private String FILE_PATH;

    @Scheduled(cron = "0 0 0 * * *")
    public void cleanTmpDir() throws IOException {
        File dir = new File(FILE_PATH);
        File[] allFiles = dir.listFiles();
        for (File file : allFiles) {
            log.info("REMOVE FILE : "+file.getName());
        }
        FileUtils.cleanDirectory(dir);
    }
}
