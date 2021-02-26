package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.modelo.Curso;

public interface CursoRepository extends JpaRepository<Curso,Long> {

	Curso findByNome(String nomeCurso);

}
