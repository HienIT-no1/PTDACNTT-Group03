package com.example.English.teaching.center.service;

import java.util.List;

import com.example.English.teaching.center.model.Course;

public interface CourseService {
    List<Course> getAllCourses();

    void saveCourse(Course course);

    void deleteCourseById(Long id);
}
