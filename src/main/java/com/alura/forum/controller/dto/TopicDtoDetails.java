package com.alura.forum.controller.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.alura.forum.model.Topic;
import com.alura.forum.model.TopicStatus;

public class TopicDtoDetails {
	private Long id;
	private String title;
	private String message;
	private LocalDateTime dateCriation;
	private String authorName;
	private TopicStatus status;
	private List<ReplyDto> reply;
	
	public TopicDtoDetails(Topic topic) {
		this.id = topic.getId();
		this.title = topic.getTitle();
		this.message = topic.getMessage();
		this.dateCriation = topic.getDateCriation();
		this.authorName = topic.getAutor().getName();
		this.status = topic.getStatus();
		this.reply = new ArrayList<>();
		this.reply.addAll(topic.getReply().stream().map(ReplyDto:: new).collect(Collectors.toList()));
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
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

	public TopicStatus getStatus() {
		return status;
	}

	public List<ReplyDto> getReply() {
		return reply;
	}

	
	
	
}
