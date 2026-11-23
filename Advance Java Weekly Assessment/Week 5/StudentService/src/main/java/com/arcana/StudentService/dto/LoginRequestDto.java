package com.arcana.StudentService.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDto {
	
	@NotBlank(message="Username should not be blank")
	private String name;
	
	@Email
	private String email;
	
	@NotBlank(message="Password should not be blank")
	private String password;
}