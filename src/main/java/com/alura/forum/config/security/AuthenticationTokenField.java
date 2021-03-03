package com.alura.forum.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alura.forum.model.User;
import com.alura.forum.repository.UserRepository;

public class AuthenticationTokenField extends OncePerRequestFilter{
	
	private TokenService tokenService;
	private UserRepository repository;
	
	public AuthenticationTokenField(TokenService tokenService, UserRepository repository) {
		this.tokenService = tokenService;
		this.repository = repository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = recoveryToken(request);
		boolean valid = tokenService.isTokenValid(token);
		if(valid) {
			authenticationClient(token);
		}
		filterChain.doFilter(request, response);
	}

	private void authenticationClient(String token) {
		Long idUser = tokenService.getIdUser(token);
		User user = repository.findById(idUser).get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String recoveryToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null|| token.isEmpty()|| !token.startsWith("Bearer ")) {
			return null;
		}
		return token.substring(7,token.length());
	} 
}
