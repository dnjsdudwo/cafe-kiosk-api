package com.onandon.cafe.cafekioskapi.service.coffee;

import com.onandon.cafe.cafekioskapi.dto.drink.Drink;
import org.springframework.stereotype.Service;

public interface DrinkService {
    public void makeDrink(Drink drink);

    public void makeIce(boolean isIce);
}
