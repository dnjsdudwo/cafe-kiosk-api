package com.onandon.cafe.cafekioskapi.service.coffee;

import com.onandon.cafe.cafekioskapi.dto.coffee.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DrinkServiceImpl implements DrinkService{

    @Override
    public void makeDrink(Coffee drink) {

        boolean booleanIsIce = Boolean.parseBoolean(drink.getIsIce());
        makeIce(booleanIsIce);
    }

    @Override
    public void makeIce(boolean isIce) {
        if(isIce) {
            String ice = "Ice";
        }

        log.info("얼음 넣고 시럽 넣고 제작");
    }


}
