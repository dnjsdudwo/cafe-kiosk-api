package com.onandon.cafe.cafekioskapi.mapper;

import com.onandon.cafe.cafekioskapi.dto.coffee.Coffee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {
    public String test() throws Exception;

    public List<Coffee> getMenuList(String type);
}
