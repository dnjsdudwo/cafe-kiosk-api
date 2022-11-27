package com.onandon.cafe.cafekioskapi.config.jwt;

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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

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

        if(request.getRequestURI().contains("login")){
            filterChain.doFilter(request,response);
            return;
        }
        if(!request.getRequestURI().contains("login") && !request.getRequestURI().contains("favicon")){
            log.info("토큰 체크");
        }
        if(JwtTokenProvider.expireToken(request.getHeader("Access-Token"))){
            getJwtRefreshFromRequest(request);
        }
        try {
            String jwt = getJwtFromRequest(request);// request에서 jwt 토큰을 꺼냄

            if(StringUtils.hasText(jwt) && JwtTokenProvider.validateToken(jwt)){
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
        String bearerToken = request.getHeader("Access-Token");
        log.info("bearerToken : " + bearerToken);
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")){
            log.info("Bearer exist");
            return bearerToken.substring("Bearer ".length());
        }
        return null;
    }

    private String getJwtRefreshFromRequest(HttpServletRequest request){
        String accessToken = request.getHeader("Access-Token");
        String refreshToken = request.getHeader("Refresh-Token");

        if(accessToken != null && refreshToken != null){
            return securityServiceImpl.validateRefreshToken(refreshToken);
        }
        return null;
    }


}
