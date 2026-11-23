package com.arcana.StudentService.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arcana.StudentService.dto.CourseRequestDto;
import com.arcana.StudentService.dto.CourseResponseDto;
import com.arcana.StudentService.service.CourseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseController {
	
	private final CourseService service;
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CourseResponseDto> createCourse(@RequestBody @Valid CourseRequestDto reqDto){
		
		CourseResponseDto resDto = service.create(reqDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(resDto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CourseResponseDto> getCourse(@PathVariable Long id){
		
		CourseResponseDto resDto = service.get(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(resDto);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CourseResponseDto> updateCourse(@RequestBody @Valid CourseRequestDto reqDt, @PathVariable Long id){
		
		CourseResponseDto resDto = service.update(reqDt, id);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(resDto);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CourseResponseDto> deleteCourse(@PathVariable Long id){
		
		CourseResponseDto resDto = service.delete(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(resDto);
	}
}
