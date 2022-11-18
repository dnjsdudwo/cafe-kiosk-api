package com.onandon.cafe.cafekioskapi.service.coffee;

import com.onandon.cafe.cafekioskapi.dto.coffee.Coffee;
import com.onandon.cafe.cafekioskapi.dto.drink.Drink;
import com.onandon.cafe.cafekioskapi.dto.item.RequestCoffee;
import com.onandon.cafe.cafekioskapi.dto.item.RequestDrink;
import com.onandon.cafe.cafekioskapi.repository.itemRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Calendar;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{

    private final itemRepository itemRepository;

    public ItemServiceImpl(itemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Coffee> addCoffee(RequestCoffee coffee) throws Exception{
        coffee.getCoffee().setImg_url(getFileUrl(coffee.getImg_file()));
        return itemRepository.CoffeeSave(coffee.getCoffee());
    }

    @Override
    public List<Drink> addDrink(RequestDrink drink) throws Exception{
        drink.getDrink().setImg_url(getFileUrl(drink.getImg_file()));
        return itemRepository.DrinkSave(drink.getDrink());
    }

    @Override
    public List<Coffee> getCoffee() {
        return itemRepository.getCoffee();
    }

    @Override
    public List<Drink> getDrink() {
        return itemRepository.getDrink();
    }


    @Value("${spring.servlet.location}")
    private String FILE_LOCATION;
    public String getFileUrl(MultipartFile img_file) throws Exception{
        Long fileName = Calendar.getInstance().getTimeInMillis();
        String filePath = FILE_LOCATION+"img_"+fileName+".jpg";
        img_file.transferTo(new File(filePath));
        return "img_"+fileName;
    }

}
