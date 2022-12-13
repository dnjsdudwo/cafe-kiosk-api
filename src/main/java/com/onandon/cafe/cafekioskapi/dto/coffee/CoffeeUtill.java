package com.onandon.cafe.cafekioskapi.dto.coffee;

import com.onandon.cafe.cafekioskapi.service.coffee.CoffeeService;
import com.onandon.cafe.cafekioskapi.service.coffee.DrinkService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class CoffeeUtill {
    public static void cafeUtill(List<Coffee> param
            , CoffeeService coffeeService
            , DrinkService drinkService) throws Exception {
        Coffee coffee = new Coffee();

        int index = 1;

        for(Coffee value : param) {
            coffee.setName(value.getName());
            coffee.setPrice(value.getPrice());
            coffee.setMenuInfo(value.getMenuInfo());
            coffee.setCount(value.getCount());
            coffee.setIsIce(value.getIsIce());
            coffee.setSize(value.getSize());
            coffee.setType(value.getType());

            if(coffee.getType() == 'c'){
                log.info(index + "번 커피==================================");
                log.info("menu = {}", coffee.getName() + " " + coffee.getSize());
                coffeeService.make(coffee);
            }else
            if(coffee.getType() == 'd') {
                log.info(index + "번 음료==================================");
                log.info("menu = {}", coffee.getName() + " " + coffee.getSize());
                drinkService.makeDrink(coffee);
            }
            index++;
        }

    }
}
