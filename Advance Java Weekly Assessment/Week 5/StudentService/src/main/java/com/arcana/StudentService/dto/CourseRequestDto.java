package com.arcana.StudentService.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CourseRequestDto {
	
	@NotBlank(message="Course Name required")
	private String courseName;
	
	@NotBlank(message="Course Code required")
	private String courseCode;
	
	@NotBlank(message="Instructor Name required")
	private String instructor;
	
	@Positive(message="Credit is always positive")
	private int credits;
}
