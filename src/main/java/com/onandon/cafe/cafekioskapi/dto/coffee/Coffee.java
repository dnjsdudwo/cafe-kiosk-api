package com.onandon.cafe.cafekioskapi.dto.coffee;

import com.onandon.cafe.cafekioskapi.dto.item.OrderItem;
import lombok.Data;

@Data
public class Coffee extends OrderItem {
    private String beans;
    private String shot;
    private String milk;
    private String ice;
}
