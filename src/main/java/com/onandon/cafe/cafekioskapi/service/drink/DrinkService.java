package com.onandon.cafe.cafekioskapi.service.drink;


import com.onandon.cafe.cafekioskapi.dto.menu.Menu;

import java.util.List;
import java.util.Map;

public interface DrinkService {

    public int makeDrink(Map<String,Object> param) throws Exception;

    public void shakeBase(String base);

    public void isIce(boolean isIce) throws Exception;

    public List<Menu> searchDrinkList(Map<String,Object> param) throws Exception;

    public int insertOrder(Map<String,Object> param) throws Exception;

}
