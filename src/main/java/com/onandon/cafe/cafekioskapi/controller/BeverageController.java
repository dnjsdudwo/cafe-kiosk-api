package com.onandon.cafe.cafekioskapi.controller;

import com.onandon.cafe.cafekioskapi.dto.coffee.Coffee;
import com.onandon.cafe.cafekioskapi.dto.item.Items;
import com.onandon.cafe.cafekioskapi.service.coffee.CoffeeService;
import com.onandon.cafe.cafekioskapi.utils.OrderUtils;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class BeverageController {

    private final CoffeeService coffeeServiceImpl;

    @PostMapping("/coffee")
    public String makeCoffee(Coffee coffee) throws Exception {
        return coffeeServiceImpl.make(coffee);
    }

    @PostMapping("/order")
    public Map<String,Object> postOrder(@RequestBody Items data) {
        return OrderUtils.getOrder(data);
    }
}
