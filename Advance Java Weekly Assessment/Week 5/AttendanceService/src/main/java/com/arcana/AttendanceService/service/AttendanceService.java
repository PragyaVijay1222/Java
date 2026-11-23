package com.arcana.AttendanceService.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.arcana.AttendanceService.client.StudentClient;
import com.arcana.AttendanceService.dto.AttendanceRequestDto;
import com.arcana.AttendanceService.dto.AttendanceResponseDto;
import com.arcana.AttendanceService.dto.StudentResponseDto;
import com.arcana.AttendanceService.entity.Attendance;
import com.arcana.AttendanceService.repository.AttendanceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttendanceService {
	
	private final AttendanceRepository repo;
	private final ModelMapper modelMapper;
	private final StudentClient studentClient;
	
	public AttendanceResponseDto create(AttendanceRequestDto reqDto) {
		
		Attendance attendance = modelMapper.map(reqDto, Attendance.class);
		
		Attendance saved = repo.save(attendance);
		
		return modelMapper.map(saved, AttendanceResponseDto.class);
	}
	
	public List<AttendanceResponseDto> getStudentAttendance(Long id){
		
		StudentResponseDto studDto = studentClient.getStudent(id);
		if(studDto == null) {
			throw new RuntimeException("Student does not exists");
		}
		
		List<Attendance> attendance = repo.findAllByStudentId(id).get();
		List<AttendanceResponseDto> resDtos = attendance.stream().map(a -> modelMapper.map(a, AttendanceResponseDto.class)).toList();
		
		return resDtos;
	}
	
	public List<AttendanceResponseDto> getCourseAttendance(Long id){
		
		List<Attendance> attendance = repo.findAllByCourseId(id).get();
		List<AttendanceResponseDto> resDtos = attendance.stream().map(a -> modelMapper.map(a, AttendanceResponseDto.class)).toList();
		
		return resDtos;
	}
	
	public AttendanceResponseDto update(Long id ,AttendanceRequestDto reqDto) {
		
		Attendance attendance = repo.findById(id).orElseThrow(() -> new RuntimeException("Invalid ID"));
		modelMapper.map(attendance, reqDto);
		repo.save(attendance);
		
		return modelMapper.map(attendance, AttendanceResponseDto.class);
	}
	
	public AttendanceResponseDto delete(Long id) {
		
		Attendance attendance = repo.findById(id).orElseThrow(() -> new RuntimeException("Invalid ID"));
		repo.deleteById(id);
		
		return modelMapper.map(attendance, AttendanceResponseDto.class);
	}
}
