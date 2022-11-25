package com.onandon.cafe.cafekioskapi.dto.item;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
public abstract class OrderItem {

    @NotBlank(message = "제품명은 필수입력사항 입니다.")
    String name;

    @NotNull(message = "제품 가격은 필수입력사항 입니다")
    @Max(value = 999999, message = "999999원을 초과할 수 없습니다.")
    Integer price;

    Integer count;
    String description;

    @NotBlank(message = "제품 타입을 입력해주세요.")
    String type;
    String img_url;
}
