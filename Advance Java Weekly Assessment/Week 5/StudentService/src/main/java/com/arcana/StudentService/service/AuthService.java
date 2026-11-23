package com.arcana.StudentService.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.arcana.StudentService.dto.AuthResponse;
import com.arcana.StudentService.dto.LoginRequestDto;
import com.arcana.StudentService.dto.RegisterRequestDto;
import com.arcana.StudentService.entity.Role;
import com.arcana.StudentService.entity.Student;
import com.arcana.StudentService.repository.StudentRepository;
import com.arcana.StudentService.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	
	private final StudentRepository repo;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;
	private final ModelMapper modelMapper;
	
	public String register(RegisterRequestDto request) {
		Student student = modelMapper.map(request, Student.class);
		student.setPassword(passwordEncoder.encode(request.getPassword()));
		student.setRole(Role.valueOf(request.getRole().toUpperCase()));
		
		if(repo.findByEmail(request.getEmail()).isPresent()) {
			throw new RuntimeException("Email already registered");
		}
		
		repo.save(student);
		
		return "User registered successfully";
	}
	
	public AuthResponse login(LoginRequestDto request) {
		
		Student student = repo.findByEmail(request.getEmail())
				.orElseThrow(() -> new RuntimeException("Student not found"));
		
		if(!passwordEncoder.matches(request.getPassword(), student.getPassword())) {
			throw new RuntimeException("Invalid credentials");
		}
		
		String token = jwtUtil.generateToken(student.getName(), student.getRole().name());
		return new AuthResponse(token, student.getName(), student.getRole().name());
	}
}
