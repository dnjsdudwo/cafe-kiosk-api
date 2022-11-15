package com.onandon.cafe.cafekioskapi.common;

import com.onandon.cafe.cafekioskapi.dto.drink.Juice;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Map;

@Log4j2
public class drinkUtil {

/*    public static void makeOrderDrink(Map<String, Object> param) {
        String type = (String) param.get("flag");

        if (type.equals("juice")) {
            drink = new Juice((String) param.get("name")
                    , (Integer) param.get("price")
                    , (boolean) param.get("isIce")
                    , (String) param.get("base"));
        }

        shakeBase(drink.getBase());
    }*/

    public static void shakeBase(String base) {
        log.info(base + "를 액체로 만드는 중............... ");
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        log.info("음료 준비완료");
    }

    public static void isIce(String name, boolean isIce) throws Exception {
        if(!isIce) {
            System.out.println(name + "는 무조건 ICE로만 가능합니다");
            throw new Exception(name+" has only hot");
        }

        log.info("ICE 선택하였습니다...");
    }


}
