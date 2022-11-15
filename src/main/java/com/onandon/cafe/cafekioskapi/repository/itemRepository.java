package com.onandon.cafe.cafekioskapi.repository;

import com.onandon.cafe.cafekioskapi.dto.coffee.Coffee;
import com.onandon.cafe.cafekioskapi.dto.drink.Drink;
import com.onandon.cafe.cafekioskapi.dto.item.Items;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    public Map<String,Object> findAll() {
        Map<String,Object> m = new HashMap<>();
        m.put("coffees",coffees);
        m.put("drinks",drinks);
        return m;
    }
}
