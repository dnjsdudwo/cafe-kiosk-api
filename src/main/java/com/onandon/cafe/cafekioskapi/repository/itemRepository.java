package com.onandon.cafe.cafekioskapi.repository;

import com.onandon.cafe.cafekioskapi.dto.coffee.Coffee;
import com.onandon.cafe.cafekioskapi.dto.drink.Drink;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@AllArgsConstructor
@Data
public class itemRepository{


    private final List<Coffee> coffees;
    private final List<Drink> drinks;


    public List<Coffee> CoffeeSave(Coffee coffee){
        coffees.add(coffee);
        return coffees;
    }

    public List<Drink> DrinkSave(Drink drink){
        drinks.add(drink);
        return drinks;
    }

    public List<Coffee> getCoffee() {
        return coffees;
    }

    public List<Drink> getDrink() {
        return drinks;
    }

}
