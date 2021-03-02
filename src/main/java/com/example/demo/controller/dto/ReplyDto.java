package com.example.demo.controller.dto;

import java.time.LocalDateTime;

import com.example.demo.model.Reply;

public class ReplyDto {
	private Long id;
	private String message;
	private LocalDateTime dateCriation;
	private String autorName;
	
	public ReplyDto(Reply reply) {
		this.id = reply.getId();
		this.message = reply.getMessage();
		this.dateCriation = reply.getDateCriation();
		this.autorName = reply.getAutor().getName();
	}

	public Long getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}

	public LocalDateTime getDateCriation() {
		return dateCriation;
	}

	public String getAutorName() {
		return autorName;
	}
	
	
}
