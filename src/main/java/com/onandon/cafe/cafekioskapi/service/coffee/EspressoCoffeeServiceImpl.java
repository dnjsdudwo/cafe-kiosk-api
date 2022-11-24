package com.onandon.cafe.cafekioskapi.service.coffee;

import com.onandon.cafe.cafekioskapi.dto.coffee.Coffee;
import com.onandon.cafe.cafekioskapi.mapper.TestMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@Qualifier("espressoService")
@RequiredArgsConstructor
public class EspressoCoffeeServiceImpl implements CoffeeService{

    @Autowired
    private final TestMapper testMapper;

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
    public void choiceHotAndIce(boolean isIce) throws Exception{

        if (isIce) {
            System.out.println("에스프레소는 무조건 핫으로 드셔야합니다.");
            throw new Exception("Espresso has only hot");
        } else {
            log.info("핫을 선택하셨습니다.");
        }
    }

    @Override
    public void make(Coffee coffee) throws Exception{
        grindBean(coffee.getBeans());
        System.out.println(testMapper.test());
        choiceHotAndIce(coffee.isIce());
    }
}
