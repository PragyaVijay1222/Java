package com.arcana.AttendanceService.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arcana.AttendanceService.entity.Attendance;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long>{
	
	Optional<List<Attendance>> findAllByStudentId(Long id);
	Optional<List<Attendance>> findAllByCourseId(Long id);
}
