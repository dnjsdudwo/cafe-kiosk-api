package com.onandon.cafe.cafekioskapi.service.coffee;

import com.onandon.cafe.cafekioskapi.dto.coffee.Coffee;

public interface CoffeeService {
    public void grindBean(String beanName);

    public void choiceHotAndIce(boolean isIce) throws Exception;

    public void make(Coffee coffee) throws Exception;
}
