package com.example.English.teaching.center.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.English.teaching.center.model.Course;
import com.example.English.teaching.center.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService{
    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }   

    @Override
    public void saveCourse(Course course) {
        courseRepository.save(course);
    }

    @Override
    public void deleteCourseById(Long id){
        courseRepository.deleteById(id);
    }
}