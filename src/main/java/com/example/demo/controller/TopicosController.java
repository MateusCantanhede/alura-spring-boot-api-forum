package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.dto.TopicoDto;
import com.example.demo.modelo.Curso;
import com.example.demo.modelo.Topico;

@RestController
public class TopicosController {
	@RequestMapping("/topicos")
	public List<TopicoDto> lista() {
		Topico topico = new Topico("Duvida", "Duvida com Spring",new Curso("Spring","Programação"));
		return TopicoDto.converter(Arrays.asList(topico,topico,topico));
	}
}