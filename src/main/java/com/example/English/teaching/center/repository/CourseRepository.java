package com.example.English.teaching.center.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.English.teaching.center.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{
    List<Course> findByCategory_NameIgnoreCase(String name);
}
