package com.onandon.cafe.cafekioskapi.repository;

import com.onandon.cafe.cafekioskapi.dto.coffee.Coffee;
import com.onandon.cafe.cafekioskapi.dto.drink.Drink;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@AllArgsConstructor
public class itemRepository{

    private List<Coffee> coffees;
    private List<Drink> drinks;


    public void CoffeeSave(Coffee coffee){
        coffees.add(coffee);
    }

    public void DrinkSave(Drink drink){
        drinks.add(drink);
    }

    public List<Coffee> findAllCoffee(){
        return coffees;
    }

    public List<Drink> findAllDrink(){
        return drinks;
    }
}
