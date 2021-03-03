package com.alura.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alura.forum.model.Course;

public interface CourseRepository extends JpaRepository<Course,Long> {

	Course findByName(String courseName);

}
