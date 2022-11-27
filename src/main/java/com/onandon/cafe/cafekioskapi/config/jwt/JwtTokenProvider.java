package com.onandon.cafe.cafekioskapi.config.jwt;

import com.onandon.cafe.cafekioskapi.dto.token.Token;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {

    private static final String BEARER_TYPE = "Bearer";
    private static String JWT_SECRET = "YXNkYXNnaW9qd2VnaW9hamVzZ2lvYXNkZ2phc2Rsa2dqYXdlb2lwZ2phc29pZWdqYXNkbGtnamFzZGxnaXdlamdpbGFzZWdqYWxzaWc=";
    private static String JWT_REFRESH_SECRET = "YXNkYXNnaW9qd2VnaW9hamVzZ2lvYXNkZ2phc2Rsa2dqYXdlb2lwZ2phc29pZWdqYXNkbGtnamFzZGxnaXdlamdpbGFzZWdqYWxzabc=";
    private static Long JWT_EXPIRATION_MS = 30000L;
    private static Long JWT_REFRESH_EXPIRATION_MS = 30000000L;
    private static Key key;


    public JwtTokenProvider() {
        byte[] keyBytes = Decoders.BASE64.decode(JWT_SECRET);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }
    public static Token generateToken(Authentication authentication){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION_MS);
        Date reExpiryDate = new Date(now.getTime() + JWT_REFRESH_EXPIRATION_MS);

        String accessToken = Jwts.builder()
                .setSubject((String) authentication.getPrincipal())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(key,SignatureAlgorithm.HS256)
                .compact();

        String refreshToken = Jwts.builder()
                .setExpiration(reExpiryDate)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        return Token.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessTokenExpiresIn(expiryDate.getTime())
                .grantType(BEARER_TYPE)
                .build();

    }

    public static String getUserIdFromJwt(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(JWT_SECRET)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(JWT_SECRET).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }

    public static Boolean expireToken(String accessToken) {
        try {
            // 검증
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(JWT_REFRESH_SECRET).build().parseClaimsJws(accessToken);

            if (claims.getBody().getExpiration().before(new Date())) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public String validateRefreshToken(String refreshToken) {
        try {
            // 검증
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(JWT_REFRESH_SECRET).build().parseClaimsJws(refreshToken);

            //refresh 토큰의 만료시간이 지나지 않았을 경우, 새로운 access 토큰을 생성합니다.
            if (!claims.getBody().getExpiration().before(new Date())) {
                return recreationAccessToken(claims.getBody().getSubject());
            }
        } catch (Exception e) {

            //refresh 토큰이 만료되었을 경우, 로그인이 필요합니다.
            return null;
        }
        return null;
    }

        public String recreationAccessToken(String id){

            Claims claims = Jwts.claims().setSubject(id); // JWT payload 에 저장되는 정보단위
            Date now = new Date();

            //Access Token
            String accessToken = Jwts.builder()
                    .setIssuedAt(now)
                    .setExpiration(new Date(now.getTime() + JWT_EXPIRATION_MS))
                    // set Expire Time
                    .signWith(key,SignatureAlgorithm.HS256)  // 사용할 암호화 알고리즘과
                    // signature 에 들어갈 secret값 세팅
                    .compact();

            return accessToken;
        }
}

