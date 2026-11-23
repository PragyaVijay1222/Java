package com.arcana.StudentService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arcana.StudentService.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	
	Optional<Student>findByName(String name);
	Optional<Student>findByEmail(String email);
}
