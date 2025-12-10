package com.example.English.teaching.center.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.English.teaching.center.model.Course;
import com.example.English.teaching.center.service.CategoryService;
import com.example.English.teaching.center.service.CourseService;

@Controller
@RequestMapping("/user/course")
public class CourseController {
    private final CourseService courseService;
    private final CategoryService categoryService;

    public CourseController(CourseService courseService, CategoryService categoryService) {
        this.courseService = courseService;
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public String getCoursesByCategory(
            @RequestParam(name = "category", required = false) String categoryName, 
            Model model) {

        List<Course> courses;

        if (categoryName != null && !categoryName.isEmpty()) {
            courses = categoryService.getCoursesByCategoryName(categoryName);
            model.addAttribute("categoryTitle", categoryName); 
        } else {
            courses = List.of();
        }
        model.addAttribute("listCourses", courses); 
        
        return "user/course"; 
    }

}
