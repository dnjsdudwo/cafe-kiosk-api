package com.onandon.cafe.cafekioskapi.controller;

import com.onandon.cafe.cafekioskapi.dto.coffee.Coffee;
import com.onandon.cafe.cafekioskapi.service.coffee.CoffeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class BeverageController {

    @Autowired
    private final CoffeeService coffeeService;

    @PostMapping("/coffee")
    public String makeCoffee(Coffee coffee) throws Exception{
        coffeeService.make(coffee);
        return "good";
    }
}
