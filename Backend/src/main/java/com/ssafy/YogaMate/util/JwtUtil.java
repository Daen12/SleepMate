package com.ssafy.YogaMate.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
public class JwtUtil {
    private static final String SALT = "YogaMate";

    // 토큰을 생성
    public String createToken(String claimId, String data) throws UnsupportedEncodingException {
        return Jwts.builder()
                .setHeaderParam("alg", "HS256")
                .setHeaderParam("type", "JWT")
                .claim(claimId, data)
                .signWith(SignatureAlgorithm.HS256, SALT.getBytes("UTF-8"))
                .compact();
    }

    // 유효성 검사
    public void valid(String token) throws UnsupportedEncodingException {
        Jwts.parser().setSigningKey(SALT.getBytes("UTF-8")).parseClaimsJws(token);
    }
}
