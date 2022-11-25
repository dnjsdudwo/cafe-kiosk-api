package com.onandon.cafe.cafekioskapi.errortest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebAppConfiguration
@SpringBootTest
public class ErrorTests {
    MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wct;

    @BeforeEach
    public void mockSetup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wct).build();
    }

    @Test
    @DisplayName("서버에러 테스트")
    public void serverErrorTest() throws Exception {
        mockMvc.perform(get("/error/server"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("커스텀 테스트")
    public void customErrorTest() throws Exception {
        mockMvc.perform(get("/error/custom"))
                .andDo(MockMvcResultHandlers.log());
    }

    @Test
    @DisplayName("파일에러 테스트")
    public void fileErrorTest() throws Exception {
        mockMvc.perform(get("/error/file"))
                .andDo(MockMvcResultHandlers.print());
    }
}
