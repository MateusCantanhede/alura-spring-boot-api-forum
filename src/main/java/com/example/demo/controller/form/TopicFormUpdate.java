package com.example.demo.controller.form;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.example.demo.model.Topic;
import com.example.demo.repository.TopicRepository;

public class TopicFormUpdate {
	@NotNull @NotEmpty @Length(min = 5)
	private String title;
	
	@NotNull @NotEmpty @Length(min = 10)
	private String message;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Topic update(Long id, TopicRepository topicRepository) {
		Topic topico = topicRepository.getOne(id);
		topico.setTitle(this.title);
		topico.setMessage(this.message);
		return topico;
		
	}
	
	
}
