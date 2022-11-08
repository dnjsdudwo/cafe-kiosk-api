package com.onandon.cafe.cafekioskapi.service.coffee;

import com.onandon.cafe.cafekioskapi.dto.coffee.Coffee;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class CoffeeServiceImpl implements CoffeeService{
    public void make(Coffee coffee) {
        grindBean(coffee.getBeans());

        choiceHotAndIce(coffee.isIce());
    }

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
    public void choiceHotAndIce(boolean isIce) {
        log.info(isIce + "를 선택하셨습니다.");
    }

}
