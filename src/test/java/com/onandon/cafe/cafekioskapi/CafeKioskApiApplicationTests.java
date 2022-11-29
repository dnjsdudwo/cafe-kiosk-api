package com.onandon.cafe.cafekioskapi;

import com.onandon.cafe.cafekioskapi.controller.BeverageController;
import com.onandon.cafe.cafekioskapi.dto.coffee.Coffee;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CafeKioskApiApplicationTests {

    @Autowired
    BeverageController beverageController;

    @Test
    @Order(1)
    @DisplayName("Normal Coffee Ice Test")
    void normalCoffeeIceTest() throws Exception {
        Coffee coffee = new Coffee();

        coffee.setName("카페모카");
        coffee.setPrice(2500);
        coffee.setMenuInfo("카페모카 정보");
        coffee.setSize("M");
        coffee.setCount(1);
        coffee.setType("coffee");
//        coffee.setIsIce(String.valueOf(true));
        coffee.setIsIce(String.valueOf(false));


        List<Coffee> list = new ArrayList<Coffee>();
        list.add(coffee);

        beverageController.allPirce(10000);
        beverageController.coffeeOrderList(list);
    }


    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:log4jdbc:mysql://localhost:3306/cafekiosk?useSSL=false&useUnicode=true&serverTimezone=Asia/Seoul";
    private static final String USER = "sjs";
    private static final String PASSWORD = "onandon12#";

    @Test
    @Order(2)
    @DisplayName("DB Test")
    public void dataBaseTest() throws Exception {
        Class.forName(DRIVER);

        try{
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("DB접속 정보 : " + connection);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
