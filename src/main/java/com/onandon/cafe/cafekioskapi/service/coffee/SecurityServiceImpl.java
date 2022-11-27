package com.onandon.cafe.cafekioskapi.service.coffee;

import com.onandon.cafe.cafekioskapi.config.jwt.Userauthentication;
import com.onandon.cafe.cafekioskapi.error.CustomException;
import com.onandon.cafe.cafekioskapi.error.ErrorCode;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SecurityServiceImpl {

    public Authentication singUp(String id, String pwd){
        if(id.equals("user") && pwd.equals("1234")) {
            Authentication authentication = new Userauthentication(id,pwd);
            return authentication;
        }
        else {
            throw new CustomException(ErrorCode.UNREGISTRY_MEMBER);
        }
    }
}
