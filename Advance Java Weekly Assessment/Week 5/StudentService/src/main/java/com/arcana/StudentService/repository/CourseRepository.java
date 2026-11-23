package com.arcana.StudentService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arcana.StudentService.entity.Course;


@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{

}
