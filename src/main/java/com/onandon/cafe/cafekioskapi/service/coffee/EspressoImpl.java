package com.onandon.cafe.cafekioskapi.service.coffee;

import com.onandon.cafe.cafekioskapi.dto.coffee.Coffee;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class EspressoImpl implements CoffeeService{
    @Override
    public void grindBean(String beanName) {
        log.info(beanName + "원두 가는 중.........");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info(beanName + "원두 갈기 끝");
    }

    @Override
    public void choiceHotAndIce(boolean isIce) throws Exception{
        System.out.println("에스프레소는 무조건 핫으로 드셔야합니다.");

        throw new Exception("Espresso has only hot");
    }

    @Override
    public void make(Coffee coffee) throws Exception{
        grindBean(coffee.getBeans());

        choiceHotAndIce(coffee.isIce());
    }
}
