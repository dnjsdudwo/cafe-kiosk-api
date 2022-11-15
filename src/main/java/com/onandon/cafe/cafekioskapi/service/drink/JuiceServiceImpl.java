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
        List<Map<String, Object>> orderList = (List<Map<String, Object>>) param.get("orderList");

        for( Map<String, Object> orderInfo : orderList ) {
            Juice juice = new Juice((String)orderInfo.get("name")
                    ,(Integer)orderInfo.get("price")
                    ,(boolean)orderInfo.get("isIce")
                    ,(String)orderInfo.get("base"));

            drinkUtil.shakeBase(juice.getBase());
            drinkUtil.isIce(juice.getName(),juice.isIce());
        }

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
