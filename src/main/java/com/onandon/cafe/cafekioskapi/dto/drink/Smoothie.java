package com.onandon.cafe.cafekioskapi.dto.drink;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Smoothie {
    private String name;
    private int price;
    private boolean isIce;
    private String base;
}
