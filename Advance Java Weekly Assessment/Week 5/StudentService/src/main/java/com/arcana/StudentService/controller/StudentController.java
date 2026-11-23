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

import com.arcana.StudentService.dto.StudentRequestDto;
import com.arcana.StudentService.dto.StudentResponseDto;
import com.arcana.StudentService.service.StudentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {
	
	private final StudentService service;
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<StudentResponseDto> createStudent(@RequestBody @Valid StudentRequestDto reqDto){
		
		StudentResponseDto resDto = service.create(reqDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(resDto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<StudentResponseDto> getStudent(@PathVariable Long id){
		
		StudentResponseDto resDto = service.get(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(resDto);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<StudentResponseDto> updateStudent(@RequestBody @Valid StudentRequestDto reqDto, @PathVariable Long id){ 
		
		StudentResponseDto resDto = service.update(reqDto, id);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(resDto);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<StudentResponseDto> deleteStudent(@PathVariable Long id){
		
		StudentResponseDto resDto = service.delete(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(resDto);
	}
}
