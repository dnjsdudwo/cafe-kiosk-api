package com.onandon.cafe.cafekioskapi.dto.coffee;

import lombok.Data;

@Data
public class Coffee {
    private String name;
    private int price;
    private String menuInfo;


    private String beans;
    private boolean isIce;
}
