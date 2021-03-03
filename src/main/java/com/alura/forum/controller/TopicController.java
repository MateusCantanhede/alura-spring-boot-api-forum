package com.alura.forum.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.alura.forum.controller.dto.TopicDto;
import com.alura.forum.controller.dto.TopicDtoDetails;
import com.alura.forum.controller.form.TopicForm;
import com.alura.forum.controller.form.TopicFormUpdate;
import com.alura.forum.model.Topic;
import com.alura.forum.repository.CourseRepository;
import com.alura.forum.repository.TopicRepository;

@RestController
@RequestMapping("/topicos")
public class TopicController {
	
	@Autowired
	private TopicRepository topicRepository;
	@Autowired
	private CourseRepository courseRepository;
	
	@GetMapping
	@Cacheable(value = "TopicList")
	public Page<TopicDto> list(@RequestParam(required = false) String courseName,
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable pagenation){

		
		if(courseName==null) {
			Page<Topic> topics = topicRepository.findAll(pagenation);
			return TopicDto.convert(topics);
		}else {
			Page<Topic> topics = topicRepository.findByCourseName(courseName, pagenation);
			return TopicDto.convert(topics);
		}
		
	}
	@PostMapping
	@Transactional
	@CacheEvict(value = "TopicList", allEntries = true)
	public ResponseEntity<TopicDto> register(@RequestBody @Valid TopicForm form, UriComponentsBuilder uriBuilder) {
		Topic topic = form.converter(courseRepository);
		topicRepository.save(topic);
		
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topic.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicDto(topic));
	}
	@GetMapping("/{id}")
	public ResponseEntity<TopicDtoDetails> detail(@PathVariable Long id) {
		Optional<Topic> topic = topicRepository.findById(id);
		if(topic.isPresent()) {
			return ResponseEntity.ok(new TopicDtoDetails(topic.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "TopicList", allEntries = true)
	public ResponseEntity<TopicDto> update(@PathVariable Long id, @RequestBody @Valid TopicFormUpdate form){
		Optional<Topic> optional = topicRepository.findById(id);
		if(optional.isPresent()) {
			Topic topico = form.update(id,topicRepository);
			return ResponseEntity.ok(new TopicDto(topico));
		}
		return ResponseEntity.notFound().build();
		
		
	}
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "TopicList", allEntries = true)
	public ResponseEntity<?> remove(@PathVariable Long id){
		Optional<Topic> optional = topicRepository.findById(id);
		if(optional.isPresent()) {
			topicRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
}