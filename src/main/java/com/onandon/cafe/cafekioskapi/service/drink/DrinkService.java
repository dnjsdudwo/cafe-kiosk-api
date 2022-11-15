package com.onandon.cafe.cafekioskapi.service.drink;


import java.util.Map;

public interface DrinkService {

    public int makeDrink(Map<String,Object> param) throws Exception;

    public void shakeBase(String base);

    public void isIce(boolean isIce) throws Exception;

}
