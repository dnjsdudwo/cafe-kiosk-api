package com.onandon.cafe.cafekioskapi.controller;

import com.onandon.cafe.cafekioskapi.dto.coffee.Coffee;
import com.onandon.cafe.cafekioskapi.dto.drink.Drink;
import com.onandon.cafe.cafekioskapi.dto.item.Items;
import com.onandon.cafe.cafekioskapi.dto.item.RequestCoffee;
import com.onandon.cafe.cafekioskapi.dto.item.RequestDrink;
import com.onandon.cafe.cafekioskapi.service.coffee.CoffeeService;
import com.onandon.cafe.cafekioskapi.service.coffee.ItemService;
import com.onandon.cafe.cafekioskapi.utils.OrderUtils;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class BeverageController {

    private final CoffeeService coffeeServiceImpl;
    private final ItemService itemServiceImpl;

    @PostMapping("/coffee")
    public String makeCoffee(Coffee coffee) throws Exception {
        return coffeeServiceImpl.make(coffee);
    }

    @PostMapping("/order")
    public Map<String,Object> postOrder(@RequestBody Items data) {
        return OrderUtils.getOrder(data);
    }

    @PostMapping("/add/coffee")
    public List<Coffee> postAddCoffee(RequestCoffee data)throws Exception{
        return itemServiceImpl.addCoffee(data);
    }
    @PostMapping("/add/drink")
    public List<Drink> postAddDrink(RequestDrink data)throws Exception{
        return itemServiceImpl.addDrink(data);
    }

    @GetMapping("/find/coffee")
    public List<Coffee> getCoffee(){
        return itemServiceImpl.getCoffee();
    }
    @GetMapping("/find/drink")
    public List<Drink> getDrink(){
        return itemServiceImpl.getDrink();
    }



}
