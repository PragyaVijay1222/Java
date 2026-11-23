package com.arcana.ResultService.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.arcana.ResultService.dto.AttendanceResponseDto;


@FeignClient(name="ATTENDANCESERVICE")
public interface AttendanceClent {
	
	@GetMapping("/student/{studentId}")
	public List<AttendanceResponseDto> getStudentAttendance(@PathVariable Long studentId);
	
}
