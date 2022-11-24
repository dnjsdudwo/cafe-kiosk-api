package com.onandon.cafe.cafekioskapi.controller;

import com.onandon.cafe.cafekioskapi.dto.coffee.Coffee;
import com.onandon.cafe.cafekioskapi.service.coffee.CoffeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class BeverageController {

    @Autowired
    private final CoffeeService normalService;
    @Autowired
    @Qualifier("espressoService")
    private final CoffeeService espressoService;

    @PostMapping("/coffee")
    public String makeCoffee(Coffee coffee) throws Exception{
        normalService.make(coffee);
        return "good";
    }

    @PostMapping("/espresso")
    public String makeEspresso(Coffee coffee) throws Exception{
        espressoService.make(coffee);
        return "good";
    }
}
