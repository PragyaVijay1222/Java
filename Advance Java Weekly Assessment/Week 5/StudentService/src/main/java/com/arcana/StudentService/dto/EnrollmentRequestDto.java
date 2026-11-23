package com.arcana.StudentService.dto;


import lombok.Data;

@Data
public class EnrollmentRequestDto {
	
	private Long studentId;
	private Long courseId;
}
