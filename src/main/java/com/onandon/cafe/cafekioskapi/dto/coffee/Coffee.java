package com.onandon.cafe.cafekioskapi.dto.coffee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coffee {
    private int menu_no;
    private String name;
    private int price;
    private String menuInfo;
    private int count;
    private String isIce;
    private String size;
    private String type;

//    private String beans;
}
