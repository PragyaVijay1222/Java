package com.arcana.StudentService.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.arcana.StudentService.dto.CourseRequestDto;
import com.arcana.StudentService.dto.CourseResponseDto;
import com.arcana.StudentService.entity.Course;
import com.arcana.StudentService.repository.CourseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseService {
	
	private final CourseRepository repo;
	private final ModelMapper modelMapper;
	
	public CourseResponseDto create(CourseRequestDto reqDto) {
		
		Course course = modelMapper.map(reqDto, Course.class);
		repo.save(course);
		
		return modelMapper.map(course, CourseResponseDto.class);
	}
	
	public CourseResponseDto get(Long id) {
		
		Course course = repo.findById(id).orElseThrow(() -> new RuntimeException("Course does noyt exists"));
		
		return modelMapper.map(course, CourseResponseDto.class);
	}
	
	public CourseResponseDto update(CourseRequestDto reqDto, Long id) {
		
		Course course = repo.findById(id).orElseThrow(() -> new RuntimeException("Course does noyt exists"));
		
		modelMapper.map(course, reqDto);
		repo.save(course);
		
		return modelMapper.map(course, CourseResponseDto.class);
	}
	
	public CourseResponseDto delete(Long id) {
		
		Course course = repo.findById(id).orElseThrow(() -> new RuntimeException("Course does noyt exists"));
		repo.deleteById(id);
		
		return modelMapper.map(course, CourseResponseDto.class);
	}
	
	public List<CourseResponseDto> getAll(){
		
		List<Course> courses = repo.findAll();
		List<CourseResponseDto> resDtos = courses.stream().map(c -> modelMapper.map(c, CourseResponseDto.class)).toList();
		
		return resDtos;
	}
}
