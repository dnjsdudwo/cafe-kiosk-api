package com.onandon.cafe.cafekioskapi.controllertest;


import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Base64Utils;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@SpringBootTest
public class SecurityAPIControllerTest {
    MockMvc mockMvc;


    @Autowired
    private WebApplicationContext wct;

    @BeforeEach
    public void mockSetup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wct).build();
    }


    @Test
    @DisplayName("시큐리티 테스트")
    public void SecurityAPIControllerTest () throws Exception{

        MvcResult result = mockMvc.perform(get("/login?id=user&pw=123"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        System.out.println("result = " + result.getResponse().getContentAsString());

    }

    @Test
    @DisplayName("랜덤 토큰 발급")
    public void randomToken() throws Exception {
        byte[] keyBytes = "asdasgiojwegioajesgioasdgjasdlkgjaweoipgjasoiegjasdlkgjasdlgiwejgilasegjalsig".getBytes();
        String a = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNjY5NjA4NjQ1LCJleHAiOjE2Njk2MDg2NzV9.5Ihb77a9L1dWhlrww3K1UeNH2OgRWnyNzimvxSx_Mz0";
        String b = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNjY5NjA4NTkxLCJleHAiOjE2Njk2MDg2MjF9.fb4APlzrxZmqU0Tl-6RYrx0Qms1eO3us9-az8hAHgdY";
        String c = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNjY5NjA4NjY0LCJleHAiOjE2Njk2MDg2OTR9.sfub2c0XnDeLnK3tjDGZ-ziQwHoGwSXwx6YiyagPWK0";
        String d = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNjY5NjA4NzA5LCJleHAiOjE2Njk2MDg3Mzl9.Ras_-WlJvc4sMQBech4k5IpbqGE6yjXsemm00eCqfGU";
        String as = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNjY5NjA5NDM5LCJleHAiOjE2Njk2MDk0Njl9.K3xIPKlXJ8HuaZQj5sEIvuwY8853Wgd9AowKhahY_DM";
        System.out.println("key = " + Base64Utils.encodeToString(keyBytes));
    }

}