package com.onandon.cafe.cafekioskapi.dto.drink;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Drink {
    private int menu_no;
    private String name;
    private int price;
    private String menuInfo;
    private int count;
    private String isIce;
    private String size;
    private String type;
}
