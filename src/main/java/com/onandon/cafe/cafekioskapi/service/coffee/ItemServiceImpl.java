package com.onandon.cafe.cafekioskapi.service.coffee;

import com.onandon.cafe.cafekioskapi.dto.coffee.Coffee;
import com.onandon.cafe.cafekioskapi.dto.drink.Drink;
import com.onandon.cafe.cafekioskapi.repository.itemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService{

    private final itemRepository itemRepository;

    @Override
    public void addCoffee(Coffee coffee) {
        itemRepository.CoffeeSave(coffee);
    }

    @Override
    public void addDrink(Drink drink) {
        itemRepository.DrinkSave(drink);
    }

    @Override
    public List<Coffee> findCoffee() {
        return itemRepository.findAllCoffee();
    }

    @Override
    public List<Drink> findDrink() {
        return itemRepository.findAllDrink();
    }

}
