package com.onandon.cafe.cafekioskapi.config;


import com.onandon.cafe.cafekioskapi.config.jwt.JwtAuthenticationEntryPoint;
import com.onandon.cafe.cafekioskapi.config.jwt.JwtAuthenticationFilter;
import com.onandon.cafe.cafekioskapi.service.coffee.SecurityServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationEntryPoint unauthorizedHandler;
    private final SecurityServiceImpl securityServiceImpl;
    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
       return  http
                .cors() // cors 설정
                .and()
                .csrf() // csrf 사이트 간 요청 위조 설정
                .disable()
                // 인증 , 허가 에러 시 공통적으로 처리해주는 부분
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 시큐리티는 기본적으로 세션을 사용하는데 여기서는 사용하지 않으니 stateless
                .and()
                // UsernamePasswordAuthentioationFilter 보다 JwtAuthentioationFilter를 먼저수행
                .addFilterBefore(new JwtAuthenticationFilter(securityServiceImpl),
                        UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/regenerate/token/**").permitAll()
                .antMatchers("/image/**").permitAll()
                .antMatchers("/**")
                .authenticated()

                .and()
                .build();
    }

}
