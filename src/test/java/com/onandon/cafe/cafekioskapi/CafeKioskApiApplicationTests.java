package com.onandon.cafe.cafekioskapi;

import com.onandon.cafe.cafekioskapi.controller.BeverageController;
import com.onandon.cafe.cafekioskapi.dto.coffee.Coffee;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CafeKioskApiApplicationTests {

    @Autowired
    BeverageController beverageController;

    @Test
    @Order(1)
    @DisplayName("Normal Coffee Ice Test")
    void normalCoffeeIceTest() {
        Coffee coffee = new Coffee();

        coffee.setIce(true);
        coffee.setBeans("test");
        coffee.setName("name");
        coffee.setPrice(1234);


        try {
            beverageController.makeCoffee(coffee);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    @Order(2)
    @DisplayName("Normal Coffee Hot Test")
    void normalCoffeeHotTest() {
        Coffee coffee = new Coffee();

        coffee.setIce(false);
        coffee.setBeans("test");
        coffee.setName("name");
        coffee.setPrice(1234);


        try {
            beverageController.makeCoffee(coffee);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Order(3)
    @DisplayName("Espresso Coffee Ice Test")
    void espressoCoffeeIceTest() {
        Coffee coffee = new Coffee();

        coffee.setIce(true);
        coffee.setBeans("test");
        coffee.setName("name");
        coffee.setPrice(1234);


        try {
            beverageController.makeEspresso(coffee);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Order(4)
    @DisplayName("Espresso Coffee Hot Test")
    void espressoCoffeeHotTest() {
        Coffee coffee = new Coffee();

        coffee.setIce(false);
        coffee.setBeans("test");
        coffee.setName("name");
        coffee.setPrice(1234);


        try {
            beverageController.makeEspresso(coffee);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
