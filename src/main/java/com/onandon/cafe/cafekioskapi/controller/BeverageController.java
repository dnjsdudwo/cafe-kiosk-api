package com.onandon.cafe.cafekioskapi.controller;

import com.fasterxml.jackson.datatype.jsr310.ser.YearSerializer;
import com.onandon.cafe.cafekioskapi.dto.coffee.Coffee;
import com.onandon.cafe.cafekioskapi.service.coffee.CoffeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class BeverageController {

    @Autowired
    private final CoffeeService coffeeService;

    @PostMapping("/coffee")
    public String makeCoffee(Coffee coffee) throws Exception{
        coffeeService.make(coffee);
        return "good";
    }


    @PostMapping("/coffee2")
    public String axiosTest(@RequestBody List<Coffee> param) {
        System.out.println("통신됨??: " + param);

        Coffee coffee = new Coffee();

        for (Coffee value : param) {
            coffee.setName(value.getName());
            coffee.setPrice(value.getPrice());
            coffee.setMenuInfo(value.getMenuInfo());
            coffee.setCount(value.getCount());
            coffee.setIsIce(value.getIsIce());
            coffee.setSize(value.getSize());

            log.info("name = {}", coffee.getName());
            log.info("price = {}", coffee.getPrice());
            log.info("menuInfo = {}", coffee.getMenuInfo());
            log.info("count = {}", coffee.getCount());
            log.info("isIce = {}", coffee.getIsIce());
            log.info("size = {}", coffee.getSize());
        }


        return "hello";
    }
}
