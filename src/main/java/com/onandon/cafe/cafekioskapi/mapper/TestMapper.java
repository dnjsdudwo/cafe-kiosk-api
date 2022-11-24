package com.onandon.cafe.cafekioskapi.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMapper {
    public String test() throws Exception;
}
