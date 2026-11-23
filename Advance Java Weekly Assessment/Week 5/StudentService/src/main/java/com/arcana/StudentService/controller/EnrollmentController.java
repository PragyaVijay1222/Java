package com.arcana.StudentService.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.arcana.StudentService.dto.CourseResponseDto;
import com.arcana.StudentService.dto.EnrollmentRequestDto;
import com.arcana.StudentService.dto.EnrollmentResponseDto;
import com.arcana.StudentService.service.EnrollmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class EnrollmentController {
	
	private final EnrollmentService service;
	
	@PostMapping("/enroll")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<EnrollmentResponseDto> enrollStudent(@RequestBody EnrollmentRequestDto reqDto){
		
		EnrollmentResponseDto resDto = service.create(reqDto);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(resDto);
	}
	
	@GetMapping("/students/{studentId}/courses")
	public ResponseEntity<List<CourseResponseDto>> getEnrolledCourse(@PathVariable Long studentId){
		
		List<CourseResponseDto> resDtos = service.get(studentId);
		
		return ResponseEntity.status(HttpStatus.OK).body(resDtos);
	}
}
