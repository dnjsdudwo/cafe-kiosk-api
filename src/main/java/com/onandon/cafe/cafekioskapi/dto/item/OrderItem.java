package com.onandon.cafe.cafekioskapi.dto.item;

import lombok.Data;


@Data
public abstract class OrderItem {
    String name;
    int price;
    String tumbler;
    Integer count;
    Boolean isMilk;
    Boolean isIce;
    String description;
    String type;
}
