package com.arcana.StudentService.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.arcana.StudentService.dto.CourseResponseDto;
import com.arcana.StudentService.dto.EnrollmentRequestDto;
import com.arcana.StudentService.dto.EnrollmentResponseDto;
import com.arcana.StudentService.entity.Course;
import com.arcana.StudentService.entity.Enrollment;
import com.arcana.StudentService.repository.CourseRepository;
import com.arcana.StudentService.repository.EnrollmentRepository;
import com.arcana.StudentService.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnrollmentService {
	
	private final EnrollmentRepository repo;
	private final StudentRepository sRepo;
	private final CourseRepository cRepo;
	private final ModelMapper modelMapper;
	
	public EnrollmentResponseDto create(EnrollmentRequestDto reqDto) {
		
		Enrollment enrollment = new Enrollment();
		
		sRepo.findById(reqDto.getStudentId()).orElseThrow(() -> new RuntimeException("Student does not exists"));
		cRepo.findById(reqDto.getCourseId()).orElseThrow(() -> new RuntimeException("Course does not exists"));
		enrollment.setCourseId(reqDto.getCourseId());
		enrollment.setStudentId(reqDto.getStudentId());
		repo.save(enrollment);
		
		return modelMapper.map(enrollment, EnrollmentResponseDto.class);
	}
	
	public List<CourseResponseDto> get(Long studentId) {
		
		List<Enrollment> enrollments = repo.findAllByStudentId(studentId).orElseThrow(() -> new RuntimeException("No enrollments found for the student "));
		List<Course> courses = enrollments.stream().map(e -> cRepo.findById(e.getCourseId()).orElseThrow(() -> new RuntimeException("Course not found with id " + e.getCourseId()))).toList();
		List<CourseResponseDto> resDtos = courses.stream().map(c -> modelMapper.map(c, CourseResponseDto.class)).toList();
		
		return resDtos;
	}
}
