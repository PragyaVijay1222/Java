package com.arcana.StudentService.dto;

import lombok.Data;

@Data
public class CourseResponseDto {
	
	private Long id;
	private String courseName;
	private String courseCode;
	private String instructor;
	private int credits;
}
