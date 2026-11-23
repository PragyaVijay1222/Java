package com.arcana.StudentService.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="enrollment")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Enrollment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long studentId;
	private Long courseId;
	private LocalDateTime enrollmentDate;
	
	@PrePersist
	public void setDate() {
		enrollmentDate = LocalDateTime.now();
	}
}
