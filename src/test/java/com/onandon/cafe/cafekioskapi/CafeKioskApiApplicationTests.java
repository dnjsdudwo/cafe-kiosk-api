package com.onandon.cafe.cafekioskapi;

import com.onandon.cafe.cafekioskapi.dto.coffee.Coffee;
import com.onandon.cafe.cafekioskapi.dto.drink.Drink;
import com.onandon.cafe.cafekioskapi.repository.itemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class CafeKioskApiApplicationTests {
    MockMvc mockMvc;

    @BeforeEach
    public void mockSetup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wct).build();
    }
    @Autowired
    private WebApplicationContext wct;

    @Value("${spring.servlet.location}")
    private String FILE_LOCATION;


    @Autowired
    private itemRepository itemRepository;



    @Test
    @DisplayName("커피추가 테스트")
    public void coffeeAddTest () throws Exception{
        MockMultipartFile file = getMockMultipartFile("img_file","jpg",FILE_LOCATION+"testImage.jpg");
        MvcResult result = mockMvc.perform(multipart("/add/coffee")
                .file(file)
                .param("coffee.name","myCoffee")
                .param("coffee.price","1000")
                .param("coffee.isIce","true")
                .param("coffee.isMilk","true"))
                .andExpect(status().isOk())
                .andReturn();


        System.out.println("price = " + itemRepository.getCoffee().get(0).getPrice()+1);
        System.out.println("isIce = " + itemRepository.getCoffee().get(0).getIsIce()+1);

        System.out.println("price type = " + itemRepository.getCoffee().get(0).getPrice().getClass().getName());
        System.out.println("isIce type = " + itemRepository.getCoffee().get(0).getIsIce().getClass().getName());

        assertThat(result.getRequest().getParameter("coffee.name")).isEqualTo("myCoffee");
        assertThat(result.getRequest().getParameter("coffee.price")).isEqualTo("1000");
        assertThat(result.getRequest().getParameter("coffee.isIce")).isEqualTo("true");
        assertThat(result.getRequest().getParameter("coffee.isMilk")).isEqualTo("true");
    }

    @Test
    @DisplayName("음료추가 테스트")
    public void drinkAddTest () throws Exception{

        MockMultipartFile file = getMockMultipartFile("img_file","jpg",FILE_LOCATION+"testImage.jpg");

        MvcResult result = mockMvc.perform(multipart("/add/drink")
                        .file(file)
        .param("drink.name","myDrink")
        .param("drink.price","3000")
        .param("drink.isIce","true")
        .param("drink.isMilk","true"))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(result.getRequest().getParameter("drink.name")).isEqualTo("myDrink");
    }

    @Test
    @DisplayName("커피 가져오기 테스트")
    public void getCoffeeTest() throws Exception{
        Coffee coffee = new Coffee();
        coffee.setName("커피1");
        coffee.setShot("2");
        coffee.setPrice(3000);
        coffee.setMilk("우유");

        itemRepository.CoffeeSave(coffee);

        MvcResult result = mockMvc.perform(get("/find/coffee"))
                .andExpect(status().isOk())
                .andReturn();

        System.out.println("result = " + result.getResponse().getContentAsString());
    }

    @Test
    @DisplayName("음료 가져오기 테스트")
    public void getDrinkTest() throws Exception{
        Drink drink = new Drink();
        drink.setName("음료1");
        drink.setPrice(2000);
        drink.setMilk("우유");

        itemRepository.DrinkSave(drink);

        MvcResult result = mockMvc.perform(get("/find/drink"))
                .andExpect(status().isOk())
                .andReturn();

        System.out.println("result = " + result.getResponse().getContentAsString());
    }

    private MockMultipartFile getMockMultipartFile(String fileName, String contentType, String path) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(path));
        return new MockMultipartFile(fileName, fileName + "." + contentType, contentType, fileInputStream);
    }

}

