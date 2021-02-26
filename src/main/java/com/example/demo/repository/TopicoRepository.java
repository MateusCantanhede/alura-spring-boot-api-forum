package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modelo.Topico;

public interface TopicoRepository extends JpaRepository<Topico,Long>{

	List<Topico> findByCursoNome(String nomeCurso);
	
}
