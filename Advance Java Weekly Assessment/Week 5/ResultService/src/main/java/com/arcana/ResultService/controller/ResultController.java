package com.arcana.ResultService.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arcana.ResultService.dto.ResultRequestDto;
import com.arcana.ResultService.dto.ResultResponseDto;
import com.arcana.ResultService.service.ResultService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/results")
public class ResultController {
	
	private final ResultService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<ResultResponseDto> findById(@PathVariable Long id){
		
		ResultResponseDto resDto = service.get(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(resDto);
	}
	
	@PostMapping
	public ResponseEntity<ResultResponseDto> createResult(@RequestBody @Valid ResultRequestDto reqDto){
		
		ResultResponseDto resDto = service.create(reqDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(resDto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ResultResponseDto> updateById(@RequestBody @Valid ResultRequestDto reqDto, @PathVariable Long id){
		
		ResultResponseDto resDto = service.update(reqDto, id);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(resDto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResultResponseDto> deleteById(@PathVariable Long id){
		
		ResultResponseDto resDto = service.delete(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(resDto);
	}
	
	@GetMapping
	public ResponseEntity<List<ResultResponseDto>> getAllResults(){
		
		List<ResultResponseDto> resDtos = service.getAll();
		
		return ResponseEntity.status(HttpStatus.OK).body(resDtos);
	}
	
	@GetMapping("/student/{studentId}")
	@CircuitBreaker(name = "studentService", fallbackMethod = "studentFallback")
	public ResponseEntity<List<ResultResponseDto>> getStudentsResult(@PathVariable Long studentId){
		
		List<ResultResponseDto> resDtos = service.getByStudentId(studentId);
		
		return ResponseEntity.status(HttpStatus.OK).body(resDtos);
	}
	
	public ResponseEntity<List<ResultResponseDto>> studentFallback(Long studentId,Throwable ex) {
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(Collections.emptyList());
	}
	
	@GetMapping("/course/{courseId}")
	public ResponseEntity<List<ResultResponseDto>> getCourseResult(@PathVariable Long courseId){
		
		List<ResultResponseDto> resDtos = service.getByCourseId(courseId);
		
		return ResponseEntity.status(HttpStatus.OK).body(resDtos);
	}
}
