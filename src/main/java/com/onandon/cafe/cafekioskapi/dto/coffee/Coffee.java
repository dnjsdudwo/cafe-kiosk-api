package com.onandon.cafe.cafekioskapi.dto.coffee;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Coffee {
    private String name;
    private String beans;
    private int price;
    private boolean isIce;
}
