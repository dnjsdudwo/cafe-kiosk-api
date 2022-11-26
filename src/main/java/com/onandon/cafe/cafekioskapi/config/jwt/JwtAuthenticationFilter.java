package com.onandon.cafe.cafekioskapi.config.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        log.info(request.getRequestURI());

        if(!request.getRequestURI().contains("login") && !request.getRequestURI().contains("favicon")){
            log.info("토큰 체크");
        }
        try {
            String jwt = getJwtFromRequest(request); // request에서 jwt 토큰을 꺼냄
            if(StringUtils.hasText(jwt) && JwtTokenProvider.validateToken(jwt)){
                String userId = JwtTokenProvider.getUserIdFromJwt(jwt); // jwt에서 사용자 id를 꺼낸다

                log.info("userId : " + userId);

                Userauthentication authentication = new Userauthentication(userId,null,null); // id를 인증한다
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); // 기본적으로 제공한 details 세팅

                SecurityContextHolder.getContext()
                        .setAuthentication(authentication); // 세션에서 계속 사용하기 위해 securitycontext에 authentication등록
            }
            else {
                if(!StringUtils.hasText(jwt)){
                    request.setAttribute("unauthorization", "401 인증키 없음");
                }
                if(JwtTokenProvider.validateToken(jwt)) {
                    request.setAttribute("unauthorization", "401-001 인증키 만료");
                }
            }
        }
        catch (Exception e) {
            log.error("security error");
        }
        filterChain.doFilter(request,response);
    }

    private String getJwtFromRequest(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        log.info("bearerToken : " + bearerToken);
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")){
            log.info("Bearer exist");
            return bearerToken.substring("Bearer ".length());
        }
        return null;
    }
}
