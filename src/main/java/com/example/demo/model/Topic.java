package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Topic {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String message;
	private LocalDateTime dateCriation = LocalDateTime.now();
	
	@Enumerated(EnumType.STRING)
	private TopicStatus status = TopicStatus.NAO_RESPONDIDO;
	
	@ManyToOne
	private User autor;
	@ManyToOne
	private Course course;
	@OneToMany(mappedBy = "topic")
	private List<Reply> reply = new ArrayList<>();
	
	public Topic() {
	}
	
	public Topic(String title, String message, Course course) {
		this.title = title;
		this.message = message;
		this.course = course;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Topic other = (Topic) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	

	public LocalDateTime getDateCriation() {
		return dateCriation;
	}

	public void setDateCriation(LocalDateTime dateCriation) {
		this.dateCriation = dateCriation;
	}

	public TopicStatus getStatus() {
		return status;
	}

	public void setStatus(TopicStatus status) {
		this.status = status;
	}

	public User getAutor() {
		return autor;
	}

	public void setAutor(User autor) {
		this.autor = autor;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public List<Reply> getReply() {
		return reply;
	}

	public void setReply(List<Reply> reply) {
		this.reply = reply;
	}

	
}