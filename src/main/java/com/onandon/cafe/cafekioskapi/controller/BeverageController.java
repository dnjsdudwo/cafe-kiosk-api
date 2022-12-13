package com.onandon.cafe.cafekioskapi.controller;

import com.onandon.cafe.cafekioskapi.dto.coffee.Coffee;
import com.onandon.cafe.cafekioskapi.dto.coffee.CoffeeUtill;
import com.onandon.cafe.cafekioskapi.dto.coffee.Order;
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

    @PostMapping("/addMenuList")
    public void addMenuList(@RequestBody Coffee param) {
        testMapper.addMenuList(param);
    }

    @PostMapping("/delMenuList")
    public void delMenuList(@RequestBody Coffee param) {
        testMapper.delMenuList(param.getMenu_no());
    }

    @PostMapping("/updMenuList")
    public void updMenuList(@RequestBody Coffee coffee) {
        testMapper.updMenuList(coffee);
    }

    @PostMapping("/getOrderList")
    public List<Order> getOrderList() {
        return testMapper.getOrderList();
    }


    @PostMapping("/allPrice")
    public int allPirce(@RequestBody int param){
        System.out.println("총액: " + param + "원");
        return param;
    }

    @PostMapping("/coffee")
    public String coffeeOrderList(@RequestBody List<Coffee> param) throws Exception {
        System.out.println("coffee 통신: " + param);

        CoffeeUtill.cafeUtill(param, coffeeService, drinkService);

        for(Coffee coffee : param) {
            if(coffee.getIsIce().equals( "false")) {
                coffee.setIsIce("HOT");
            }else {
                coffee.setIsIce("ICE");
            }
            testMapper.addOrderList(coffee);
        }

        return "coffee success";
    }

}
