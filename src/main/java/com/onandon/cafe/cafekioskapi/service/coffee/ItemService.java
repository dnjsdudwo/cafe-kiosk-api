package com.onandon.cafe.cafekioskapi.service.coffee;

import com.onandon.cafe.cafekioskapi.dto.coffee.Coffee;
import com.onandon.cafe.cafekioskapi.dto.drink.Drink;
import com.onandon.cafe.cafekioskapi.dto.item.Items;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ItemService {
    void addCoffee(Coffee coffee);
    void addDrink(Drink drink);

    List<Coffee> findCoffee();

    List<Drink> findDrink();

    Map<String,Object> findAll();
}
