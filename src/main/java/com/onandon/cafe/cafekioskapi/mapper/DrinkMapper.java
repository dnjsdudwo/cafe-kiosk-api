package com.onandon.cafe.cafekioskapi.mapper;

import com.onandon.cafe.cafekioskapi.dto.menu.Menu;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

import java.util.Map;

@Mapper
public interface DrinkMapper {

  public List<Menu> selectDrinkList(Map<String,Object> param);

  public int insertOrder(Map<String,Object> param);

  public int insertOrderDetail(Map<String,Object> param);




}