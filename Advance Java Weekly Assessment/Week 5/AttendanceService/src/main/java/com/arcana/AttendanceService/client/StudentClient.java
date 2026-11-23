package com.arcana.AttendanceService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.arcana.AttendanceService.config.FeignClientConfig;
import com.arcana.AttendanceService.dto.StudentResponseDto;

@FeignClient(name="STUDENTSERVICE", configuration = FeignClientConfig.class)
public interface StudentClient {
	
	@GetMapping("/students/{id}")
	StudentResponseDto getStudent(@PathVariable Long id);
}
