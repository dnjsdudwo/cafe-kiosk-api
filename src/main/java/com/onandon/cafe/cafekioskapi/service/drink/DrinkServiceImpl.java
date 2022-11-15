package com.onandon.cafe.cafekioskapi.service.drink;

import com.onandon.cafe.cafekioskapi.dto.drink.Smoothie;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Map;

@Log4j2
@Service
@Primary
public class DrinkServiceImpl implements DrinkService{

    @Override
    public int makeDrink(Map<String, Object> param) throws Exception {
        Smoothie smoothie = new Smoothie((String)param.get("name")
                                        ,(Integer)param.get("price")
                                        ,(boolean)param.get("isIce")
                                        ,(String) param.get("base"));

        shakeBase(smoothie.getBase());
        isIce(smoothie.isIce());
        return 1;
    }

    @Override
    public void shakeBase(String base) {
        log.info(base + "를 물과 우유와함께 가는중............... ");
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        log.info("쉐이크 준비 완료");

    }

    @Override
    public void isIce(boolean isIce) throws Exception {
        if(!isIce) {
            System.out.println("스무디는 무조건 ICE로만 가능합니다");
            throw new Exception("Smoothie has only hot");
        }

        log.info("ICE 선택하였습니다...");
    }


}
