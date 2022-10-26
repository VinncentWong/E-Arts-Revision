package com.earts.earts.util;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.earts.earts.entity.Human;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil<T extends Human> {
	
	@Value("${JWT_SECRET_KEY}")
	private String secretKey;
	
	public String generateToken(IJwt<T> jwt, T obj) {
		return jwt.generateToken(obj);
	}
	
	public String implementationGenerateToken(T obj) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("id", obj.getId());
		claims.put("username", obj.getUsername());
		claims.put("createdAt", obj.getCreatedAt());
		claims.put("role", obj.getRole());
		String token = Jwts.builder()
							.setClaims(claims)
							.signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(secretKey.getBytes()))
							.setExpiration(new Date(System.currentTimeMillis() + 1000000L))
							.compact();
		return token;
	}
}