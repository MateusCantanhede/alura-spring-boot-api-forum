package br.com.alura.forum.controller.dto;

public class tokenDto {

	private String token;
	private String type;

	public tokenDto(String token, String type) {
		this.token = token;
		this.type = type;
	}

	public String getToken() {
		return token;
	}

	public String getType() {
		return type;
	}

	
}
