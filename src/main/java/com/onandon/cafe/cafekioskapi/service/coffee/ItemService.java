package com.onandon.cafe.cafekioskapi.service.coffee;

import com.onandon.cafe.cafekioskapi.dto.coffee.Coffee;
import com.onandon.cafe.cafekioskapi.dto.drink.Drink;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemService {
    void addCoffee(Coffee coffee);
    void addDrink(Drink drink);

    List<Coffee> findCoffee();

    List<Drink> findDrink();
}
