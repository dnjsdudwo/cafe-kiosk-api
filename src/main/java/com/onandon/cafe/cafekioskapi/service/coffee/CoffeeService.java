package com.onandon.cafe.cafekioskapi.service.coffee;


import java.util.Map;

public interface CoffeeService {
    public void grindBean(String beanName);

    public void choiceHotAndIce(boolean isIce) throws Exception;

    public int make(Map<String,Object> param) throws Exception;

}
