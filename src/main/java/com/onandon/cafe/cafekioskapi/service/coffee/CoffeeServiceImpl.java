package com.onandon.cafe.cafekioskapi.service.coffee;

import com.onandon.cafe.cafekioskapi.dto.coffee.Coffee;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class CoffeeServiceImpl implements CoffeeService{
    @Override
    public void make(Coffee coffee) {
//        grindBean(coffee.getBeans());

        boolean booleanIsIce = Boolean.parseBoolean(coffee.getIsIce());
        choiceHotAndIce(booleanIsIce);
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
        String hotAndIce = "Hot";
        if(isIce){
            hotAndIce = "Ice";
        }

        if(isIce) {
           log.info(hotAndIce + "를(을) 선택! 얼음을 넣었습니다.");
        }else{
            log.info(hotAndIce + "를(을) 선택! 물 끓이는 중...");
            try{
                Thread.sleep(2000);
            }catch (Exception e){
                e.printStackTrace();
            }
            log.info("물이 끓고 있어요");
        }

    }

}
