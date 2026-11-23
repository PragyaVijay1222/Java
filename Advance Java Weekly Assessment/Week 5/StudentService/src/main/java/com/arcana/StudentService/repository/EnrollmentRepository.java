package com.arcana.StudentService.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arcana.StudentService.entity.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long>{
	
	Optional<List<Enrollment>>findAllByStudentId(Long id);
}
