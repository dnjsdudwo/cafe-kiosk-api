package com.onandon.cafe.cafekioskapi.controllertest;

import com.onandon.cafe.cafekioskapi.dto.coffee.Coffee;
import com.onandon.cafe.cafekioskapi.dto.drink.Drink;
import org.assertj.core.api.Assertions;
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
import org.springframework.web.context.WebApplicationContext;

import java.nio.file.NoSuchFileException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@SpringBootTest
public class ControllerTests {
    MockMvc mockMvc;


    @Autowired
    private WebApplicationContext wct;

    @BeforeEach
    public void mockSetup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wct).build();
    }




    @Autowired
    private com.onandon.cafe.cafekioskapi.repository.itemRepository itemRepository;


    @Test
    @DisplayName("커피추가 테스트")
    public void coffeeAddTest () throws Exception{
        JSONObject json = new JSONObject();
        json.put("name","ads");
        json.put("milk","우유");
        json.put("price",400);
        json.put("shot","1");
        json.put("ice","hot");
        json.put("tumbler","사용안함");
        json.put("type","coffee");

            MvcResult result = mockMvc.perform(post("/add/coffee")
                            .contentType("application/json")
                            .content(String.valueOf(json)))
                    .andExpect(status().is5xxServerError())
                    .andDo(MockMvcResultHandlers.log())
                    .andReturn();

    }

    @Test
    @DisplayName("음료추가 테스트")
    public void drinkAddTest () throws Exception{

        JSONObject json = new JSONObject();
        json.put("name","ads");
        json.put("milk","우유");
        json.put("price",3000);

            MvcResult result = mockMvc.perform(post("/add/drink")
                            .contentType("application/json")
                            .content(String.valueOf(json)))
                    .andExpect(status().is4xxClientError())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();


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

        Assertions.assertThat(itemRepository.getCoffees().get(0).getName()).isEqualTo("커피1");
        Assertions.assertThat(itemRepository.getCoffees().get(0).getShot()).isEqualTo("2");
        Assertions.assertThat(itemRepository.getCoffees().get(0).getPrice()).isEqualTo(3000);
        Assertions.assertThat(itemRepository.getCoffees().get(0).getMilk()).isEqualTo("우유");
    }

    @Test
    @DisplayName("음료 가져오기 테스트")
    public void getDrinkTest() throws Exception{
        Drink drink = new Drink();
        drink.setName("음료1");
        drink.setPrice(2000);
        drink.setMilk("우유");

        itemRepository.DrinkSave(drink);


        MvcResult result = mockMvc.perform(get("/find/drink/"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        Assertions.assertThat(itemRepository.getDrinks().get(0).getName()).isEqualTo("음료1");
        Assertions.assertThat(itemRepository.getDrinks().get(0).getPrice()).isEqualTo(2000);
        Assertions.assertThat(itemRepository.getDrinks().get(0).getMilk()).isEqualTo("우유");

    }
}
