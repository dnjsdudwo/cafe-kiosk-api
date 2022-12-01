package com.onandon.cafe.cafekioskapi.controller;

import com.onandon.cafe.cafekioskapi.dto.menu.Menu;
import com.onandon.cafe.cafekioskapi.service.coffee.CoffeeService;
import com.onandon.cafe.cafekioskapi.service.drink.DrinkService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class BeverageController {

    @Autowired
    private final CoffeeService coffeeService;

    @Autowired
    private DrinkService drinkService;

    @Autowired
    @Qualifier("juiceServiceImpl")
    private DrinkService juiceService;

    @PostMapping("/coffee")
    public int makeCoffee(@RequestBody Map<String,Object> param) throws Exception{
        System.out.println("통신성공"+param);
        return coffeeService.make(param);
    }

    @PostMapping("/drink")
    public int makeDrink(@RequestBody Map<String,Object> param) throws Exception{
        System.out.println("통신성공" + param);
        return drinkService.makeDrink(param);
    }

    @PostMapping("/searchDrinkList")
    public List<Menu> searchDrinkList() throws Exception{
        System.out.println("통신성공");
        return drinkService.searchDrinkList();
    }
}
