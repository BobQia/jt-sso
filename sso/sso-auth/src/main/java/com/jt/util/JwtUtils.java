package com.jt.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {
    private static String secret="AAABBBCCCDDDEEE";
    /**基于负载和算法创建token信息*/
    public static String generatorToken(Map<String,Object> map){
        return Jwts.builder()
                .setClaims(map)
                .setExpiration(new Date(System.currentTimeMillis()+30*60*1000))
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();//签约,创建token
    }
    /**解析token获取数据*/
    public static Claims getClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
    /**判定token是否失效*/
    public static boolean isTokenExpired(String token){
        Date expiration=getClaimsFromToken(token).getExpiration();
        return expiration.before(new Date());
    }
}
