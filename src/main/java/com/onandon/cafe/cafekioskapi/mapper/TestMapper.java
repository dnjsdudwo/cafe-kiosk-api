package com.onandon.cafe.cafekioskapi.mapper;

import com.onandon.cafe.cafekioskapi.dto.coffee.Coffee;
import com.onandon.cafe.cafekioskapi.dto.coffee.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {
    public List<Coffee> getMenuList(String type);

    public void addMenuList(Coffee coffee);
    public void delMenuList(int menu_no);
    public void updMenuList(Coffee coffee);

    public void addOrderList(Coffee coffee);

    public List<Order> getOrderList();
}
