package com.onandon.cafe.cafekioskapi.dto.item;

import com.onandon.cafe.cafekioskapi.dto.coffee.Coffee;
import com.onandon.cafe.cafekioskapi.dto.drink.Drink;
import lombok.Data;

import java.util.List;

@Data
public class Items {
    private List<Coffee> coffees;
    private List<Drink> drinks;
}
