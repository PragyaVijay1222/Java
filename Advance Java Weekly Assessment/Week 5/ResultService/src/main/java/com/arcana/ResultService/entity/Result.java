package com.arcana.ResultService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="result")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Result {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long resultId;
	
	private Long studentId;
	private Long courseId;
	private String examType;
	private double marksObtained;
	private double maxMarks;
	private String grade;
	
}
