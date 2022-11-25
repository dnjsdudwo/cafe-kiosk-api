package com.onandon.cafe.cafekioskapi.dto.drink;

import com.onandon.cafe.cafekioskapi.dto.item.OrderItem;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Drink extends OrderItem {

    @NotBlank(message = "우유를 선택해주세요.")
    private String milk;

    @NotBlank(message = "핫/아이스 를 선택해주세요.")
    private String ice;

    @NotBlank(message = "텀블러 사용유무를 선택해주세요.")
    private String tumbler;

    private Boolean isMilk;
    private Boolean isIce;
}
