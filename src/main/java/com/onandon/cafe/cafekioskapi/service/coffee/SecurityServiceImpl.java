package com.onandon.cafe.cafekioskapi.service.coffee;

import com.onandon.cafe.cafekioskapi.config.jwt.JwtTokenProvider;
import com.onandon.cafe.cafekioskapi.config.jwt.Userauthentication;
import com.onandon.cafe.cafekioskapi.dto.token.RefreshToken;
import com.onandon.cafe.cafekioskapi.dto.token.Token;
import com.onandon.cafe.cafekioskapi.error.CustomException;
import com.onandon.cafe.cafekioskapi.error.ErrorCode;
import com.onandon.cafe.cafekioskapi.repository.RefreshTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
@AllArgsConstructor
public class SecurityServiceImpl {

    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    public ResponseEntity singUp(String id, String pwd, HttpServletResponse response){
        if(id.equals("user") && pwd.equals("1234")) {
            Userauthentication authentication = new Userauthentication(id,null,null);
            Token token = jwtTokenProvider.generateToken(authentication);
            response.setHeader("AccessToken",token.getAccessToken());
            response.setHeader("RefreshToken",token.getRefreshToken());
            return ResponseEntity.ok().build();
        }
        else {
            throw new CustomException(ErrorCode.UNREGISTRY_MEMBER);
        }
    }

    public String validateRefreshToken(String accessToken,String refreshToken){
        String createdAccessToken = jwtTokenProvider.validateRefreshToken(accessToken,refreshToken);
        return createdAccessToken;
    }

    public String getRefreshToken(){
        return refreshTokenRepository.findRefreshToken();
    }
}
