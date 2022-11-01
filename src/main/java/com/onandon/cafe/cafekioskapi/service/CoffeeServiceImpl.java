package com.onandon.cafe.cafekioskapi.service;

import com.onandon.cafe.cafekioskapi.dto.Coffee;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class CoffeeServiceImpl implements CoffeeService{
    @Override
    public String makeCoffee(Coffee coffee) {
        grindBean(coffee.getBeans());

        choiceWaterAndMilk(coffee.getBeans());

        choiceHotAndIce(coffee.getName());
        return null;
    }

    public String grindBean(String beanName) {
        log.info(beanName + "원두 가는 중.........");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info(beanName + "원두 갈기 끝");
        return "성공";
    }

    public String choiceWaterAndMilk(String choice) {
        log.info(choice + "를 선택하셨습니다.");
        return choice;
    }

    public String choiceHotAndIce(String choice) {
        log.info(choice + "를 선택하셨습니다.");
        return choice;
    }
}
