package com.onandon.cafe.cafekioskapi.service.coffee;

import com.onandon.cafe.cafekioskapi.dto.coffee.Coffee;


public interface CoffeeService {
    void grindBean(String beanName);
    String make(Coffee coffee) throws Exception;

}
