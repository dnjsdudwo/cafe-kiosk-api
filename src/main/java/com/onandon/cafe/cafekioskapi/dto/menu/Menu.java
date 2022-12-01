package com.onandon.cafe.cafekioskapi.dto.menu;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Menu {

    private int menuNo;
    private int categoryNo;
    private String menuNm;
    private int price;
    private String image;

}
