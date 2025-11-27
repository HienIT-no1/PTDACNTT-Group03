package com.example.English.teaching.center.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.English.teaching.center.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{
    
}
