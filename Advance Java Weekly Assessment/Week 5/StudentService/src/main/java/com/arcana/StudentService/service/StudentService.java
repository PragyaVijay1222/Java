package com.arcana.StudentService.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.arcana.StudentService.dto.StudentRequestDto;
import com.arcana.StudentService.dto.StudentResponseDto;
import com.arcana.StudentService.entity.Student;
import com.arcana.StudentService.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {
	
	private final StudentRepository repo;
	private final ModelMapper modelMapper;
	
	public StudentResponseDto create(StudentRequestDto reqDto) {
		
		Student student = modelMapper.map(reqDto, Student.class);
		repo.save(student);
		
		return modelMapper.map(student, StudentResponseDto.class);
	}
	
	public StudentResponseDto get(Long id) {
		
		Student student = repo.findById(id).orElseThrow(() -> new RuntimeException("Student does not exists"));
		
		return modelMapper.map(student, StudentResponseDto.class);
	}
	
	public StudentResponseDto update(StudentRequestDto reqDto, Long id) {
		
		Student student = repo.findById(id).orElseThrow(() -> new RuntimeException("Student does not exists"));
		modelMapper.map(reqDto, student);
		repo.save(student);
		
		return modelMapper.map(student, StudentResponseDto.class);
		
	}
	
	public StudentResponseDto delete(Long id) {
		
		Student student = repo.findById(id).orElseThrow(() -> new RuntimeException("Student does not exists"));
		repo.deleteById(id);
		
		return modelMapper.map(student, StudentResponseDto.class);
	}
	
	public List<StudentResponseDto> getAll(){
		
		List<Student> students = repo.findAll();
		List<StudentResponseDto> resDtos = students.stream().map(s -> modelMapper.map(s, StudentResponseDto.class)).toList();
		
		return resDtos;
	}
	
}
