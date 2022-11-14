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
        int index = 0;
        Map<String,Object> resultMap = new HashMap<>();
        List<Coffee> coffees = new ArrayList<>();
        List<Drink> drinks = new ArrayList<>();

        for(Coffee coffee: item.getCoffees()){
            for(int i=0; i<item.getCoffees().get(index).getCount(); i++){
                coffee.setName(item.getCoffees().get(index).getName());
                coffee.setShot(item.getCoffees().get(index).getShot());
                coffee.setMilk(item.getCoffees().get(index).getMilk());
                coffee.setIce(item.getCoffees().get(index).getIce());
                coffee.setTumbler(item.getCoffees().get(index).getTumbler());
                coffees.add(coffee);
            }
            index ++;
        }
        resultMap.put("coffees",coffees);
        return resultMap;
    }
}
