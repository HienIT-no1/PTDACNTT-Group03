package com.example.English.teaching.center.service;

import java.util.List;
import java.util.Optional;

import com.example.English.teaching.center.model.Category;
import com.example.English.teaching.center.model.Course;

public interface CategoryService{
    List<Category> getAllCategories();

    Optional<Category> getCategoryById(Long categoryId);

    List<Course> getCoursesByCategoryName(String name);
}
