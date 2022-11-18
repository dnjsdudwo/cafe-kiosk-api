package com.onandon.cafe.cafekioskapi.service.coffee;

import com.onandon.cafe.cafekioskapi.dto.coffee.Coffee;
import com.onandon.cafe.cafekioskapi.dto.drink.Drink;
import com.onandon.cafe.cafekioskapi.dto.item.Items;
import com.onandon.cafe.cafekioskapi.dto.item.RequestCoffee;
import com.onandon.cafe.cafekioskapi.dto.item.RequestDrink;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public interface ItemService {
    List<Coffee> addCoffee(RequestCoffee coffee) throws Exception;
    List<Drink> addDrink(RequestDrink drink) throws Exception;

    List<Coffee> getCoffee();
    List<Drink> getDrink() ;

}
