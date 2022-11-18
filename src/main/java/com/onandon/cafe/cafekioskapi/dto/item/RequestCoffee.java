package com.onandon.cafe.cafekioskapi.dto.item;


import com.onandon.cafe.cafekioskapi.dto.coffee.Coffee;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RequestCoffee {
    private Coffee coffee;
    private MultipartFile img_file;
}
