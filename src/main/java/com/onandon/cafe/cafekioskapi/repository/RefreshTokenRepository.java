package com.onandon.cafe.cafekioskapi.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class RefreshTokenRepository {
    private Map<String,String> refreshTokenList;

    public void saveRefreshToken(String token){
        refreshTokenList.put("refreshToken",token);
    }

    public String findRefreshToken(){
        return refreshTokenList.get("refreshToken");
    }
}
