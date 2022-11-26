package com.onandon.cafe.cafekioskapi.config.jwt;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class Userauthentication extends UsernamePasswordAuthenticationToken {
    public Userauthentication(String principal, String credentials) {
        super(principal, credentials);
    }

    public Userauthentication(String principal, String credentials, List<GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
