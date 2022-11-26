package com.onandon.cafe.cafekioskapi.api;

import com.onandon.cafe.cafekioskapi.config.jwt.JwtTokenProvider;
import com.onandon.cafe.cafekioskapi.config.jwt.Userauthentication;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityAPIController {

    @GetMapping("/login")
    public String login(@RequestParam String id,String pw){
        if(id.equals("user") && pw.equals("1234")) {
            Authentication authentication = new Userauthentication(id,null,null);
            String token = JwtTokenProvider.generateToken(authentication);
            return token;
        }
        return "error";
    }
}
