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

            log.info(coffee.getName());
            log.info("" + coffee.getPrice());
            log.info(coffee.getMenuInfo());
            log.info(Integer.toString(coffee.getCount()));
        }


        return "hello";
    }
}
