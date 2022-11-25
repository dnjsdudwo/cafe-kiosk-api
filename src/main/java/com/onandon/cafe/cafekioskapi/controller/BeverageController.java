package com.onandon.cafe.cafekioskapi.controller;

import com.onandon.cafe.cafekioskapi.dto.coffee.Coffee;
import com.onandon.cafe.cafekioskapi.dto.drink.Drink;
import com.onandon.cafe.cafekioskapi.dto.item.Items;
import com.onandon.cafe.cafekioskapi.service.coffee.CoffeeService;
import com.onandon.cafe.cafekioskapi.service.coffee.ItemService;
import com.onandon.cafe.cafekioskapi.utils.OrderUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity postOrder(@RequestBody Items data) {
        return ResponseEntity.ok(OrderUtils.getOrder(data));
    }
    @PostMapping("/add/coffee")
    public ResponseEntity postAddCoffee(@RequestBody @Validated Coffee coffee) throws Exception{
        return ResponseEntity.ok(itemServiceImpl.addCoffee(coffee));
    }
    @PostMapping("/add/drink")
    public ResponseEntity postAddDrink(@RequestBody Drink drink) throws Exception{
        return ResponseEntity.ok(itemServiceImpl.addDrink(drink));
    }
    @GetMapping("/find/coffee")
    public ResponseEntity getCoffee(){
        return ResponseEntity.ok(itemServiceImpl.getCoffee());
    }
    @GetMapping("/find/drink")
    public ResponseEntity getDrink(){
        return ResponseEntity.ok(itemServiceImpl.getDrink());
    }


}
