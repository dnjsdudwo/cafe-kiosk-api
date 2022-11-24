package com.onandon.cafe.cafekioskapi.service.coffee;

import com.onandon.cafe.cafekioskapi.dto.coffee.Coffee;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@Primary
public class NormalCoffeeServiceImpl implements CoffeeService{
    public void make(Coffee coffee) {
        grindBean(coffee.getBeans());

        choiceHotAndIce(coffee.isIce());
    }

    @Override
    public void grindBean(String beanName) {
        log.info(beanName + "원두 가는 중.........");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info(beanName + "원두 갈기 끝");
    }

    @Override
    public void choiceHotAndIce(boolean isIce) {
        String choice = "얼음";

        if (!isIce) {
            choice = "핫";
        }

        log.info(choice + "을 선택하셨습니다.");
    }

}
