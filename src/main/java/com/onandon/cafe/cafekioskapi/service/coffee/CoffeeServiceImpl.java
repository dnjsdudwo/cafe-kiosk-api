package com.onandon.cafe.cafekioskapi.service.coffee;

import com.onandon.cafe.cafekioskapi.dto.coffee.Coffee;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Map;

@Log4j2
@Service
public class CoffeeServiceImpl implements CoffeeService{
    public int make(Map<String,Object> param) {
        Coffee coffee = new Coffee((String) param.get("name"), (String) param.get("beans"), (Integer) param.get("price"),(boolean)param.get("isIce"));
        grindBean(coffee.getBeans());

        choiceHotAndIce(coffee.isIce());
        return 1;
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
        log.info(isIce + "를 선택하셨습니다.");
    }

}
