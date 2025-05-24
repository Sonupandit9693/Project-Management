package com.sonu.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import javax.crypto.SecretKey;
import java.util.Date;

public class JwtProvider {

    static SecretKey secretKey = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
//
    public static String generateToken(Authentication authentication) {
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        String authoritiesString = authorities.stream().map(GrantedAuthority::getAuthority)
//                .reduce("", (a, b) -> a + "," + b);

        String token = Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(JwtConstant.JWT_EXPIRATION_TIME)))
                .claim("email", authentication.getName())
                .signWith(secretKey)
                .compact();

        return token;
    }

    public  static String getEmailFromToken(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
        return claims.get("email").toString();
    }
}
