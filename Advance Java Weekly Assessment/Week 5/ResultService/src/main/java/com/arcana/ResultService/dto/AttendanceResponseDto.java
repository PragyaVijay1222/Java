package com.arcana.ResultService.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AttendanceResponseDto {
	
	private Long attendanceId;
	private Long studentId;
	private Long courseId;
	private LocalDateTime date;
	private String status;
}
