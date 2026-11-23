package com.arcana.StudentService.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arcana.StudentService.dto.AuthResponse;
import com.arcana.StudentService.dto.LoginRequestDto;
import com.arcana.StudentService.dto.RegisterRequestDto;
import com.arcana.StudentService.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
	
	private final AuthService authService;
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterRequestDto request){
		return ResponseEntity.ok(authService.register(request));
	}
	
	@PostMapping("/login")public ResponseEntity<AuthResponse> login(@RequestBody LoginRequestDto request){
		return ResponseEntity.ok(authService.login(request));
	}
}