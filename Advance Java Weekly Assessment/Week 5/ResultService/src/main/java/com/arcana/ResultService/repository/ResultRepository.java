package com.arcana.ResultService.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arcana.ResultService.entity.Result;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long>{
	
	Optional<List<Result>> findAllByStudentId(Long id);
	Optional<List<Result>> findAllByCourseId(Long id);
	
}
