package com.onandon.cafe.cafekioskapi.dto.coffee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coffee {
    private String name;
    private int price;
    private String menuInfo;
    private int count;

    private String beans;
    private boolean isIce;
}
