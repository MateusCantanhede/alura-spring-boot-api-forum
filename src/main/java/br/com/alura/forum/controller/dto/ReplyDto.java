package br.com.alura.forum.controller.dto;

import java.time.LocalDateTime;

import br.com.alura.forum.model.Reply;

public class ReplyDto {
	private Long id;
	private String message;
	private LocalDateTime dateCriation;
	private String authorName;
	
	public ReplyDto(Reply reply) {
		this.id = reply.getId();
		this.message = reply.getMessage();
		this.dateCriation = reply.getDateCriation();
		this.authorName = reply.getAuthor().getName();
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

	public String getAuthorName() {
		return authorName;
	}
	
	
}
