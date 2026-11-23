package com.arcana.AttendanceService.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.arcana.AttendanceService.dto.AttendanceRequestDto;
import com.arcana.AttendanceService.dto.AttendanceResponseDto;
import com.arcana.AttendanceService.service.AttendanceService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attendance")
public class AttendanceController {
	
	private final AttendanceService service;
	
	@PostMapping
	public ResponseEntity<AttendanceResponseDto> markAttendance(@RequestBody @Valid AttendanceRequestDto reqDto){
		
		AttendanceResponseDto resDto = service.create(reqDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(resDto);
	}
	
	@GetMapping("/student/{studentId}")
	@CircuitBreaker(name = "studentService", fallbackMethod = "studentFallback")
	public ResponseEntity<List<AttendanceResponseDto>> getStudentAttendance(@PathVariable Long studentId){
		
		
		List<AttendanceResponseDto> resDtos = service.getStudentAttendance(studentId);
		
		return ResponseEntity.status(HttpStatus.OK).body(resDtos);
	}
	
	public ResponseEntity<List<AttendanceResponseDto>> studentFallback(Long studentId,Throwable ex) {
		ex.printStackTrace();
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(Collections.emptyList());
	}
	
	@GetMapping("/course/{courseId}")
	public ResponseEntity<List<AttendanceResponseDto>> getCourseAttendance(@PathVariable Long courseId){
		
		List<AttendanceResponseDto> resDtos = service.getCourseAttendance(courseId);
		
		return ResponseEntity.status(HttpStatus.OK).body(resDtos);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<AttendanceResponseDto> updateAttendance(@RequestBody @Valid AttendanceRequestDto reqDto, @PathVariable Long id){
		
		AttendanceResponseDto resDto = service.update(id, reqDto);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(resDto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<AttendanceResponseDto> deleteRecord(@PathVariable Long id){
		
		AttendanceResponseDto resDto = service.delete(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(resDto);
	}
}
