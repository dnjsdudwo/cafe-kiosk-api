package com.onandon.cafe.cafekioskapi.config.jwt;

import com.onandon.cafe.cafekioskapi.error.CustomException;
import com.onandon.cafe.cafekioskapi.error.ErrorCode;
import com.onandon.cafe.cafekioskapi.service.coffee.SecurityServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final SecurityServiceImpl securityServiceImpl;

    public JwtAuthenticationFilter(SecurityServiceImpl securityServiceImpl) {
        this.securityServiceImpl = securityServiceImpl;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        log.info(request.getRequestURI());

        Map<String,String> token = getTokenFromCookies(request);
        if(request.getRequestURI().contains("login") || request.getRequestURI().contains("/regenerate/token") || request.getRequestURI().contains("/image")){
            filterChain.doFilter(request,response);
            return;
        }
        if(!request.getRequestURI().contains("login") && !request.getRequestURI().contains("favicon")){
            log.info("토큰 체크");
        }
        try {
            String jwt = getJwtFromRequest(token);// request에서 jwt 토큰을 꺼냄
            if(StringUtils.hasText(jwt) && JwtTokenProvider.validateToken(jwt,response)){
                String userId = JwtTokenProvider.getUserIdFromJwt(jwt); // jwt에서 사용자 id를 꺼낸다

                log.info("userId : " + userId);

                Userauthentication authentication = new Userauthentication(userId,null,null); // id를 인증한다

                SecurityContextHolder.getContext()
                        .setAuthentication(authentication); //  securitycontext에 authentication(인증)등록
            }
            else {
                if(!StringUtils.hasText(jwt)){
                    request.setAttribute("unauthorization", "401 인증키 없음");
                }
                if(JwtTokenProvider.validateToken(jwt,response)) {
                    request.setAttribute("unauthorization", "401-001 인증키 만료");
                }
            }
        }
        catch (Exception e) {
            log.error("security error");
        }
        filterChain.doFilter(request,response);
    }

    private String getJwtFromRequest(Map token){
        String bearerToken = (String) token.get("accessToken");
        log.info("bearerToken : " + bearerToken);
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")){
            log.info("Bearer exist");
            return bearerToken.substring("Bearer ".length());
        }
        return null;
    }

    private Map<String,String> getTokenFromCookies(HttpServletRequest request){
        Map<String,String> token = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for (Cookie cookie: cookies) {
                token.put(cookie.getName(),"Bearer "+cookie.getValue());
            }
        }
        return token;
    }


}
