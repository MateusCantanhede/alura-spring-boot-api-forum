package com.example.demo.controller;

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

import com.example.demo.controller.dto.TopicoDto;
import com.example.demo.controller.dto.TopicoDtoDetalhes;
import com.example.demo.controller.form.TopicoForm;
import com.example.demo.controller.form.TopicoFormUpdate;
import com.example.demo.modelo.Topico;
import com.example.demo.repository.CursoRepository;
import com.example.demo.repository.TopicoRepository;

@RestController
@RequestMapping("/topicos")
public class TopicosController {
	
	@Autowired
	private TopicoRepository topicoRepository;
	@Autowired
	private CursoRepository cursoRepository;
	
	@GetMapping
	@Cacheable(value = "TopicList")
	public Page<TopicoDto> Lista(@RequestParam(required = false) String nomeCurso,
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable pagenation){

		
		if(nomeCurso==null) {
			Page<Topico> topicos = topicoRepository.findAll(pagenation);
			return TopicoDto.converter(topicos);
		}else {
			Page<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso, pagenation);
			return TopicoDto.converter(topicos);
		}
		
	}
	@PostMapping
	@Transactional
	@CacheEvict(value = "TopicList", allEntries = true)
	public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {
		Topico topico = form.converter(cursoRepository);
		topicoRepository.save(topico);
		
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicoDto(topico));
	}
	@GetMapping("/{id}")
	public ResponseEntity<TopicoDtoDetalhes> detalhar(@PathVariable Long id) {
		Optional<Topico> topico = topicoRepository.findById(id);
		if(topico.isPresent()) {
			return ResponseEntity.ok(new TopicoDtoDetalhes(topico.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "TopicList", allEntries = true)
	public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid TopicoFormUpdate form){
		Optional<Topico> optional = topicoRepository.findById(id);
		if(optional.isPresent()) {
			Topico topico = form.atualizar(id,topicoRepository);
			return ResponseEntity.ok(new TopicoDto(topico));
		}
		return ResponseEntity.notFound().build();
		
		
	}
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "TopicList", allEntries = true)
	public ResponseEntity<?> remover(@PathVariable Long id){
		Optional<Topico> optional = topicoRepository.findById(id);
		if(optional.isPresent()) {
			topicoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
}