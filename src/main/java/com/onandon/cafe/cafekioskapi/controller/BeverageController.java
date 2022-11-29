package com.onandon.cafe.cafekioskapi.controller;

import com.onandon.cafe.cafekioskapi.dto.coffee.Coffee;
import com.onandon.cafe.cafekioskapi.dto.drink.Drink;
import com.onandon.cafe.cafekioskapi.mapper.TestMapper;
import com.onandon.cafe.cafekioskapi.service.coffee.CoffeeService;
import com.onandon.cafe.cafekioskapi.service.coffee.DrinkService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class BeverageController {

    @Autowired
    private final CoffeeService coffeeService;
    @Autowired
    private final DrinkService drinkService;

    @Autowired
    private TestMapper testMapper;

    @PostMapping("/getMenuList")
    public List<Coffee> getMenuList(@RequestParam String type) {
        return testMapper.getMenuList(type);
    }


    @PostMapping("/allPrice")
    public int allPirce(@RequestBody int param){
        System.out.println("총액: " + param + "원");
        return param;
    }

    @PostMapping("/coffee")
    public String coffeeOrderList(@RequestBody List<Coffee> param) throws Exception {
        System.out.println("coffee 통신: " + param);


        Coffee coffee = new Coffee();

        int index = 1;
        for (Coffee value : param) {
            coffee.setName(value.getName());
            coffee.setPrice(value.getPrice());
            coffee.setMenuInfo(value.getMenuInfo());
            coffee.setCount(value.getCount());
            coffee.setIsIce(value.getIsIce());
            coffee.setSize(value.getSize());
            coffee.setType(value.getType());

            log.info(index + "번 커피==================================");
            log.info("menu = {}", coffee.getName() + " " + coffee.getSize());
            coffeeService.make(coffee);

            index++;
        }


        return "coffee success";
    }


    @PostMapping("/drink")
    public String drinkOrderList(@RequestBody List<Drink> param) {
        System.out.println("drink 통신: " + param);

        Drink drink = new Drink();

        int index = 1;
        for (Drink value : param) {
            drink.setName(value.getName());
            drink.setPrice(value.getPrice());
            drink.setMenuInfo(value.getMenuInfo());
            drink.setCount(value.getCount());
            drink.setIsIce(value.getIsIce());
            drink.setSize(value.getSize());
            drink.setType(value.getType());

            log.info(index + "번 음료==================================");
            log.info("menu = {}", drink.getName() + " " + drink.getSize());
            drinkService.makeDrink(drink);


            index++;
        }


        return "drink success";
    }

}
