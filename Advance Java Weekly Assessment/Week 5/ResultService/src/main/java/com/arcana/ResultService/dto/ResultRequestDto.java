package com.arcana.ResultService.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ResultRequestDto {
	
	@Positive
	private Long studentId;
	
	@Positive
	private Long courseId;
	
	@NotBlank(message="Exam type should not be blank")
	private String examType;
	
	@Positive
	private double marksObtained;
	
	@Positive
	private double maxMarks;
	
	@NotBlank(message="Grade should not be blank")
	private String grade;
}
