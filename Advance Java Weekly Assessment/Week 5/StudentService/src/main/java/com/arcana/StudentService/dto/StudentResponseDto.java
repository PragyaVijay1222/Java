package com.arcana.StudentService.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class StudentResponseDto {
	
	private Long id;
	private String name;
	private String email;
	private String department;
	private int semester;
	private LocalDateTime createdAt;
}
