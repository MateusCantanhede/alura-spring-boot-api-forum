package com.alura.forum.controller.dto;

import java.time.LocalDateTime;
import org.springframework.data.domain.Page;

import com.alura.forum.model.Topic;

public class TopicDto {
	private Long id;
	private String title;
	private String message;
	private LocalDateTime dateCriation;
	
	public TopicDto(Topic topic) {
		this.id = topic.getId();
		this.title = topic.getTitle();
		this.message = topic.getMessage();
		this.dateCriation = topic.getDateCriation();
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
	public static Page<TopicDto> convert(Page<Topic> topicos) {
		return topicos.map(TopicDto::new);
	}
	
}
