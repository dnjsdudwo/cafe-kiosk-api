package com.onandon.cafe.cafekioskapi.dto.drink;

import com.onandon.cafe.cafekioskapi.dto.item.OrderItem;
import lombok.Data;

@Data
public class Drink extends OrderItem {
    private String milk;
    private String ice;
}
