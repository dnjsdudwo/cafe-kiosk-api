package com.onandon.cafe.cafekioskapi.dto;

import lombok.Data;

@Data
public class Coffee {
    private String name;
    private String beans;
    private int price;
    private boolean isIce;
}
