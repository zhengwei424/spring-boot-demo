package com.zhengwei.security.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * jwt工具类
 * https://www.baeldung.com/spring-security-sign-jwt-token
 */
public class JwtUtil {

	// 有效期
	public static final Long JWT_TTL = 60 * 60 * 1000L;  // 1小时

    // 长度要足够长（当前是64个字符），保证generalKey方法生成的字节长度达标，否则方法会报错
	public static final String SECRET_KEY = "a1B2c3D4e5F6g7H8i9J0k1L2m3N4o5P6q7R8s9T0u1V2w3X4y5Z6";

	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static String createJWT(String subject) {
		JwtBuilder builder = getJwtBuilder(subject, null, getUUID());
		return builder.compact();
	}

	public static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		if (ttlMillis == null) {
			ttlMillis = JWT_TTL;
		}
		long expMillis = nowMillis + ttlMillis;
		Date expDate = new Date(expMillis);
		return Jwts.builder().id(uuid)// 唯一id
				.subject(subject)// 主题
				.issuer("zhengwei")// 签发者
				.issuedAt(now)// 签发时间
				.expiration(expDate)// 过期时间
				.signWith(generalKey(), Jwts.SIG.HS256);
	}

	public static JwtBuilder getJwtBuilder(String subject, SecretKey secretKey, Long ttlMillis, String uuid) {
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		if (ttlMillis == null) {
			ttlMillis = JWT_TTL;
		}
		long expMillis = nowMillis + ttlMillis;
		Date expDate = new Date(expMillis);
		return Jwts.builder().id(uuid)// 唯一id
				.subject(subject)// 主题
				.issuer("zhengwei")// 签发者
				.issuedAt(now)// 签发时间
				.expiration(expDate)// 过期时间
//				.signWith(secretKey, Jwts.SIG.HS256);
				.signWith(secretKey);
	}

	/**
	 * 生成secretKey,加密解密都会用到
	 *
	 * @return
	 */
	public static SecretKey generalKey() {
//		return Jwts.SIG.HS256.key().build();
		byte[] keyBytes = Base64.getDecoder().decode(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
		return new SecretKeySpec(keyBytes, "HmacSHA256");
	}

	/**
	 * 解析
	 *
	 * @param jwt
	 * @return
	 * @throws Exception
	 */
	public static Claims parseJWT(String jwt) throws Exception {
		return Jwts.parser().verifyWith(generalKey()).build().parseSignedClaims(jwt).getPayload();

	}

	public static Claims parseJWT(String jwt, SecretKey secretKey) throws Exception {
		return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(jwt).getPayload();
	}

//	public static void main(String[] args) {
//		String jwt = createJWT("zhengwei");
//		System.out.println(jwt);
//
//		try {
//			Claims a = parseJWT(jwt);
//			System.out.println(a.getSubject());
//			System.out.println(a.getIssuer());
//			System.out.println(a.getIssuedAt());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
