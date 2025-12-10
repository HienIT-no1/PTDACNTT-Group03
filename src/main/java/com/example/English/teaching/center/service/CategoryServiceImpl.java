package com.example.English.teaching.center.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.English.teaching.center.model.Category;
import com.example.English.teaching.center.model.Course;
import com.example.English.teaching.center.repository.CategoryRepository;
import com.example.English.teaching.center.repository.CourseRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CourseRepository courseRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository,
                                CourseRepository courseRepository
    ){
        this.categoryRepository = categoryRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    @Override
    public List<Course> getCoursesByCategoryName(String name) {
        return courseRepository.findByCategory_NameIgnoreCase(name);
    }
}
