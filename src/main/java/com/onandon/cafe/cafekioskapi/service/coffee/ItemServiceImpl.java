package com.onandon.cafe.cafekioskapi.service.coffee;

import com.onandon.cafe.cafekioskapi.dto.coffee.Coffee;
import com.onandon.cafe.cafekioskapi.dto.drink.Drink;
import com.onandon.cafe.cafekioskapi.repository.FileRepository;
import com.onandon.cafe.cafekioskapi.repository.itemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{

    private final itemRepository itemRepository;
    private final FileRepository fileRepository;
    private final FileAPIService fileAPIServiceImpl;

    public ItemServiceImpl(itemRepository itemRepository, FileRepository fileRepository, FileAPIService fileAPIServiceImpl) {
        this.itemRepository = itemRepository;
        this.fileRepository = fileRepository;
        this.fileAPIServiceImpl = fileAPIServiceImpl;
    }

    @Override
    public List<Coffee> addCoffee(Coffee coffee) throws Exception{
        fileAPIServiceImpl.fileMoveDir(coffee.getImg_url());
        return itemRepository.CoffeeSave(coffee);
    }

    @Override
    public List<Drink> addDrink(Drink drink) throws Exception{
        fileAPIServiceImpl.fileMoveDir(drink.getImg_url());
        return itemRepository.DrinkSave(drink);
    }

    @Override
    public List<Coffee> getCoffee() {
        return itemRepository.getCoffee();
    }

    @Override
    public List<Drink> getDrink() {
        return itemRepository.getDrink();
    }


}
