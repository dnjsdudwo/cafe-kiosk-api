package com.onandon.cafe.cafekioskapi.utils;

import com.onandon.cafe.cafekioskapi.dto.coffee.Coffee;
import com.onandon.cafe.cafekioskapi.dto.drink.Drink;
import com.onandon.cafe.cafekioskapi.dto.item.Items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class OrderUtils {
    public static Map<String,Object> getOrder(Items item){
        int coffeeIndex = 0;
        int drinkIndex = 0;
        Map<String,Object> resultMap = new HashMap<>();
        List<Coffee> coffees = new ArrayList<>();
        List<Drink> drinks = new ArrayList<>();

        for(Coffee coffee: item.getCoffees()){
            for(int i=0; i<item.getCoffees().get(coffeeIndex).getCount(); i++){
                coffee.setName(item.getCoffees().get(coffeeIndex).getName());
                coffee.setShot(item.getCoffees().get(coffeeIndex).getShot());
                coffee.setMilk(item.getCoffees().get(coffeeIndex).getMilk());
                coffee.setIce(item.getCoffees().get(coffeeIndex).getIce());
                coffee.setTumbler(item.getCoffees().get(coffeeIndex).getTumbler());
                coffees.add(coffee);
            }
            coffeeIndex ++;
        }

        for(Drink drink: item.getDrinks()){
            for(int i=0; i<item.getDrinks().get(drinkIndex).getCount(); i++){
                drink.setName(item.getDrinks().get(drinkIndex).getName());
                drink.setMilk(item.getDrinks().get(drinkIndex).getMilk());
                drink.setIce(item.getDrinks().get(drinkIndex).getIce());
                drink.setTumbler(item.getDrinks().get(drinkIndex).getTumbler());
                drinks.add(drink);
            }
            drinkIndex ++;
        }

        resultMap.put("coffees",coffees);
        resultMap.put("drink",drinks);
        return resultMap;
    }
}
