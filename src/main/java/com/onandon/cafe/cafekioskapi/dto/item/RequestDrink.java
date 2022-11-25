package com.onandon.cafe.cafekioskapi.dto.item;

import com.onandon.cafe.cafekioskapi.dto.drink.Drink;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RequestDrink {
    private Drink drink;
    private MultipartFile img_file;

}
