package com.arcana.StudentService.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(name="Auth Response DTO")
public class AuthResponse {
	
	private String token;
	
	private String name;
	
	private String role;
}