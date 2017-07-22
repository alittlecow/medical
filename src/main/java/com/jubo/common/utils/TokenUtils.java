package com.jubo.common.utils;

import com.jubo.modules.sys.dao.SysUserDao;
import com.jubo.modules.sys.entity.SysUserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;
import java.util.Date;

/**
 * @author pengxiao
 * @date 2017/7/22
 */
public class TokenUtils {
    private static long VALIDITY_TIME_MS = 3 * 60 * 1000;
    private static Key secret = MacProvider.generateKey(SignatureAlgorithm.HS512);

    public static String create(SysUserEntity user) {
        return Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + VALIDITY_TIME_MS))
                .setSubject(user.getUsername())
                .claim("userId", user.getUserId())
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }


    public static SysUserEntity parse(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        SysUserEntity user = new SysUserEntity();
        user.setUserId(claims.get("userId", Long.class));
        return user;
    }
}
