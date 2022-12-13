package com.onandon.cafe.cafekioskapi.service.coffee;

import com.onandon.cafe.cafekioskapi.dto.coffee.Coffee;

public interface DrinkService {
    public void makeDrink(Coffee drink);

    public void makeIce(boolean isIce);
}
