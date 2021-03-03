package com.alura.forum.controller.form;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoguinForm {
	private String email;
	private String passWord;
	
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getEmail() {
		return email;
	}
	public String getPassWord() {
		return passWord;
	}
	public UsernamePasswordAuthenticationToken convert() {
		return new UsernamePasswordAuthenticationToken(email,passWord);
	}
	
	
}
