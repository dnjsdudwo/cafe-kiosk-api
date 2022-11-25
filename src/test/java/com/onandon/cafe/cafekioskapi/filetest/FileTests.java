package com.onandon.cafe.cafekioskapi.filetest;

import com.onandon.cafe.cafekioskapi.error.CustomException;
import com.onandon.cafe.cafekioskapi.error.ErrorCode;
import com.onandon.cafe.cafekioskapi.repository.FileRepository;
import com.onandon.cafe.cafekioskapi.service.coffee.FileAPIService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@SpringBootTest
public class FileTests {
    MockMvc mockMvc;
    @Value("${spring.servlet.tempLocation}")
    private String FILE_TMP_LOCATION;

    @Autowired
    private WebApplicationContext wct;

    @BeforeEach
    public void mockSetup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wct).build();
    }
    @Autowired
    private FileAPIService fileAPIServiceImpl;
    @Autowired
    private FileRepository fileRepository;


    @Test
    @DisplayName("파일 업로드 테스트")
    public void postFileUpload() throws Exception {
        MockMultipartFile file = getMockMultipartFile("img_file","jpg",FILE_TMP_LOCATION+"testImage.jpg");

        MvcResult result = mockMvc.perform(multipart("/file/upload")
                        .file(file))
                .andExpect(status().isCreated())
                .andReturn();


        System.out.println("fileName = " + result.getResponse().getHeader("Location"));

        
    }

    @Test
    @DisplayName("파일 디렉토리 이동 테스트")
    public void fileMoveDirTest() throws Exception {
        try {
            MockMultipartFile file = getMockMultipartFile("img_file","jpg",FILE_TMP_LOCATION+"testImage.jpg");
            fileAPIServiceImpl.fileMoveDir("testImage.jpg");
        }
        catch (FileNotFoundException e){
            throw new CustomException(ErrorCode.FILE_NOT_FOUND_ERROR);
        }
    }






    @Test
    @DisplayName("디렉토리 비우기 테스트")
    public void cleanTmpDirTest() throws IOException {
        File dir = new File(FILE_TMP_LOCATION);
        File[] allFiles = dir.listFiles();
        for (File file : allFiles) {
            System.out.println("REMOVE FILE : "+file.getName());
        }
        FileUtils.cleanDirectory(dir);
    }

    private MockMultipartFile getMockMultipartFile(String fileName, String contentType, String path) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(path));
        return new MockMultipartFile(fileName, fileName + "." + contentType, contentType, fileInputStream);
    }
}
