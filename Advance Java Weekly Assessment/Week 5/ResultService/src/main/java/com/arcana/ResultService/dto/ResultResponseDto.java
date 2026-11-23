package com.arcana.ResultService.dto;

import lombok.Data;

@Data
public class ResultResponseDto {
	
	private Long resultId;
	private Long studentId;
	private Long courseId;
	private String examType;
	private double marksObtained;
	private double maxMarks;
	private String grade;
}
