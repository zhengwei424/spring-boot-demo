package com.zhengwei.security.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * jwt工具类
 */
public class JwtUtil {

    // 有效期
    public static final Long JWT_TTL = 60 * 60 * 1000L;  // 1小时

    // 秘钥明文
    public static final String JWT_KEY = "zhengwei";

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String createJWT(String subject) {
        JwtBuilder builder = getJwtBuilder(subject, null, getUUID());
        return builder.compact();
    }

    public static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if (ttlMillis == null) {
            ttlMillis = JWT_TTL;
        }
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        return Jwts.builder().setId(uuid) // 唯一id
                .setSubject(subject) // 主题
                .setIssuer("zhengwei")// 签发者
                .setIssuedAt(now) // 签发时间
                .signWith(signatureAlgorithm, secretKey) // 签名加密算法及秘钥
                .setExpiration(expDate); // 过期时间
    }

    /**
     * 生成加密后的秘钥
     * @return
     */
    public static SecretKey generalKey() {
        byte[] encodeKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        return new SecretKeySpec(encodeKey, 0, encodeKey.length, "AES");
    }

    /**
     * 解析
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
//        return Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(jwt).getBody();
        return Jwts.parser().decryptWith(secretKey).build().parseSignedClaims(jwt).getPayload();
    }
}
