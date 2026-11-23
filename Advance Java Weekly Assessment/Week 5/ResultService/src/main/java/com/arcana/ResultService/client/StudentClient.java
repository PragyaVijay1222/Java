package com.arcana.ResultService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.arcana.ResultService.dto.StudentResponseDto;


@FeignClient(name="STUDENTSERVICE")
public interface StudentClient {
	
	@GetMapping("/students/{id}")
	StudentResponseDto getStudent(@PathVariable Long id);
}