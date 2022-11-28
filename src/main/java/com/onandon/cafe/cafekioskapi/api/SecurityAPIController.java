package com.onandon.cafe.cafekioskapi.api;

import com.onandon.cafe.cafekioskapi.config.jwt.JwtTokenProvider;
import com.onandon.cafe.cafekioskapi.config.jwt.Userauthentication;
import com.onandon.cafe.cafekioskapi.dto.token.Token;
import com.onandon.cafe.cafekioskapi.service.coffee.SecurityServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@AllArgsConstructor
public class SecurityAPIController {

    private final SecurityServiceImpl securityServiceImpl;
    @GetMapping("/login")
    public void login(@RequestParam String id, String pw, HttpServletResponse response){
            securityServiceImpl.singUp(id,pw,response);
    }

    @PostMapping("/regenerate/token")
    public String regenerateToken(@RequestBody Token token){
        return securityServiceImpl.validateRefreshToken(token.getAccessToken(),token.getRefreshToken());
    }
}
