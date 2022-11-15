package com.onandon.cafe.cafekioskapi.service.drink;

import com.onandon.cafe.cafekioskapi.common.drinkUtil;
import com.onandon.cafe.cafekioskapi.dto.drink.Juice;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Log4j2
@Service
public class JuiceServiceImpl implements DrinkService {
    @Override
    public int makeDrink(Map<String, Object> param) throws Exception {
        Juice juice = new Juice((String) param.get("name")
                ,(Integer) param.get("price")
                ,(boolean)param.get("isIce")
                ,(String) param.get("base"));

        shakeBase(juice.getBase());
        isIce(juice.isIce());

        return 1;
    }

    @Override
    public void shakeBase(String base) {
        log.info(base + "즙을 짜는 중............... ");
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        log.info("쥬스 준비 완료");
    }

    @Override
    public void isIce(boolean isIce) throws Exception {
        if(!isIce) {
            System.out.println("쥬스는 무조건 ICE로만 가능합니다");
            throw new Exception("Smoothie has only hot");
        }

        log.info("ICE 선택하였습니다...");
    }

}
