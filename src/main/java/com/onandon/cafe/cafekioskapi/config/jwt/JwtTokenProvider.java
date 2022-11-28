package com.onandon.cafe.cafekioskapi.config.jwt;

import com.onandon.cafe.cafekioskapi.dto.token.Token;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.security.Key;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {

    private static final String BEARER_TYPE = "Bearer";
    private static String JWT_SECRET = "YXNkYXNnaW9qd2VnaW9hamVzZ2lvYXNkZ2phc2Rsa2dqYXdlb2lwZ2phc29pZWdqYXNkbGtnamFzZGxnaXdlamdpbGFzZWdqYWxzaWc=";
    private static String JWT_REFRESH_SECRET = "YXNkYXNnaW9qd2VnaW9hamVzZ2lvYXNkZ2phc2Rsa2dqYXdlb2lwZ2phc29pZWdqYXNkbGtnamFzZGxnaXdlamdpbGFzZWdqYWxzabc=";
    private static Long JWT_EXPIRATION_MS = 30000L;
    private static Long JWT_REFRESH_EXPIRATION_MS = 1231231231L;
    private static Key accessKey;
    private static Key refreshKey;


    public JwtTokenProvider() {
        byte[] accessKeyBytes = Decoders.BASE64.decode(JWT_SECRET);
        byte[] refreshKeyBytes = Decoders.BASE64.decode(JWT_REFRESH_SECRET);
        this.accessKey = Keys.hmacShaKeyFor(accessKeyBytes);
        this.refreshKey = Keys.hmacShaKeyFor(refreshKeyBytes);
    }
    public static Token generateToken(Authentication authentication){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION_MS);
        Date reExpiryDate = new Date(now.getTime() + JWT_REFRESH_EXPIRATION_MS);

        String accessToken = Jwts.builder()
                .setSubject((String) authentication.getPrincipal())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(accessKey,SignatureAlgorithm.HS256)
                .compact();

        String refreshToken = Jwts.builder()
                .setSubject((String) authentication.getPrincipal())
                .setIssuedAt(new Date())
                .setExpiration(reExpiryDate)
                .signWith(refreshKey, SignatureAlgorithm.HS512)
                .compact();

        return Token.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessTokenExpiresIn(expiryDate.getTime())
                .refreshTokenExpiresIn(reExpiryDate.getTime())
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

    public static boolean validateToken(String accessToken,HttpServletResponse response) {
        try {
            Jwts.parserBuilder().setSigningKey(JWT_SECRET).build().parseClaimsJws(accessToken);
            return true;
        }  catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        }  catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        }  catch (ExpiredJwtException e){
            log.info("만료된 JWT 토큰입니다.");
            response.setStatus(500);
            response.setHeader("REGENERATE","RE");
        }  catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }


    public static String validateRefreshToken(String accessToken,String refreshToken) {
        try {
            // 검증
            Claims accessClaims = Jwts.parserBuilder().setSigningKey(JWT_SECRET).build().parseClaimsJws(accessToken).getBody(); //access 토큰이 만료되었는지 체크합니다.
            log.info("ACCESS 토큰이 만료되지 않았습니다.");
            return null;
        } catch (ExpiredJwtException e) {
            Claims refreshClaims = Jwts.parserBuilder().setSigningKey(JWT_REFRESH_SECRET).build().parseClaimsJws(refreshToken).getBody();
            if(refreshClaims.getExpiration().after(new Date())){
                return recreationAccessToken(refreshClaims.getSubject());
            }
            else {
                //refresh 토큰이 만료되었을 경우, 로그인이 필요합니다.
                log.info("REFRESH 토큰이 만료되었습니다.");
                return null;
            }
        }
    }

        public static String recreationAccessToken(String id){

            Claims claims = Jwts.claims().setSubject(id); // JWT payload 에 저장되는 정보단위 여기선 유저 아이디를 subject로 넣었습니다
            Date now = new Date();

            //Access Token
            String accessToken = Jwts.builder()
                    .setSubject(claims.getSubject())
                    .setIssuedAt(now)
                    .setExpiration(new Date(now.getTime() + JWT_EXPIRATION_MS))
                    // set Expire Time
                    .signWith(accessKey,SignatureAlgorithm.HS256)  // 사용할 암호화 알고리즘과
                    // signature 에 들어갈 secret값 세팅
                    .compact();

            return accessToken;
        }
}

