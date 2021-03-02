package com.example.demo.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	@Value("${forum.jwt.expiration}")
	private String expiration;
	
	@Value("${forum.jwt.secret}")
	private String secret;
	
	public String generateToken(Authentication authentication) {
		User logado = (User) authentication.getPrincipal();
		Date today = new Date();
		Date expiratonDate = new Date(today.getTime()+ Long.parseLong(expiration));
		return Jwts.builder()
				.setIssuer("API do forum da alura")
				.setSubject(logado.getId().toString())
				.setIssuedAt(today)
				.setExpiration(expiratonDate)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

}
