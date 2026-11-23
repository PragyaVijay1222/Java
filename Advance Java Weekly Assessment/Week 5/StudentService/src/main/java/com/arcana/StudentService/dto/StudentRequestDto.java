package com.arcana.StudentService.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class StudentRequestDto {
	
	@NotBlank(message="Name is required")
	private String name;
	
	@NotBlank(message="Email is required")
	private String email;
	
	@NotBlank(message="Password is required")
	private String password;
	
	@NotBlank(message="Department is required")
	private String department;
	
	@Positive(message="Semester is always positive")
	private int semester;
}
