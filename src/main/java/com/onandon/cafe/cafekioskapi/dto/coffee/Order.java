package com.onandon.cafe.cafekioskapi.dto.coffee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private int order_no;
    private String name;
    private int price;
    private int count;
    private String isIce;
    private String size;
    private String resDate;
}
