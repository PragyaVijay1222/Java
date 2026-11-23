package com.arcana.StudentService.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class EnrollmentResponseDto {
	
	private Long id;
	private Long studentId;
	private Long courseId;
	private LocalDateTime enrollmentDate;
}
