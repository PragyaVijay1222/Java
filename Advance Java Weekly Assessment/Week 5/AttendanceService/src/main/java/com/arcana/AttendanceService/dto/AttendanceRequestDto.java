package com.arcana.AttendanceService.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AttendanceRequestDto {
	
	private Long studentId;
	private Long courseId;
	
	@NotBlank(message="Status must not be blank")
	private String status;
}
