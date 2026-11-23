package com.arcana.ResultService.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.arcana.ResultService.client.StudentClient;
import com.arcana.ResultService.dto.ResultRequestDto;
import com.arcana.ResultService.dto.ResultResponseDto;
import com.arcana.ResultService.dto.StudentResponseDto;
import com.arcana.ResultService.entity.Result;
import com.arcana.ResultService.repository.ResultRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResultService {
	
	private final ResultRepository repo;
	private final ModelMapper modelMapper;
	private final StudentClient studentClient;
	
	public List<ResultResponseDto> getAll(){
		
		List<Result> result = repo.findAll();
		List<ResultResponseDto> resDtos = result.stream().map(r -> modelMapper.map(r, ResultResponseDto.class)).toList();
		
		return resDtos;
	}
	
	public List<ResultResponseDto> getByStudentId(Long studentId) {
		
		StudentResponseDto studDto = studentClient.getStudent(studentId);
		if(studDto == null) {
			throw new RuntimeException("Student does not exists");
		}
		
		List<Result> result = repo.findAllByStudentId(studentId).get();
		List<ResultResponseDto> resDtos = result.stream().map(r -> modelMapper.map(r, ResultResponseDto.class)).toList();
		
		return resDtos;
	}
	
	public List<ResultResponseDto> getByCourseId(Long courseId) {
		
		List<Result> result = repo.findAllByCourseId(courseId).get();
		List<ResultResponseDto> resDtos = result.stream().map(r -> modelMapper.map(r, ResultResponseDto.class)).toList();
		
		return resDtos;
	}
	
	public ResultResponseDto create(ResultRequestDto reqDto) {
		
		Result result = modelMapper.map(reqDto, Result.class);
		repo.save(result);
		
		return modelMapper.map(result, ResultResponseDto.class);
	}
	
	public ResultResponseDto update(ResultRequestDto reqDto, Long id) {
		
		Result result = repo.findById(id).orElseThrow(() -> new RuntimeException("Record does not exists"));
		modelMapper.map(result, reqDto);
		repo.save(result);
		
		return modelMapper.map(result, ResultResponseDto.class);
	}
	
	public ResultResponseDto delete(Long id) {
		
		Result result = repo.findById(id).orElseThrow(() -> new RuntimeException("Record does not exists"));
		repo.deleteById(id);
		
		return modelMapper.map(result, ResultResponseDto.class);
	}
	
	public ResultResponseDto get(Long id) {
		
		Result result = repo.findById(id).orElseThrow(() -> new RuntimeException("Record does not exists"));
		
		return modelMapper.map(result, ResultResponseDto.class);
	}
}
