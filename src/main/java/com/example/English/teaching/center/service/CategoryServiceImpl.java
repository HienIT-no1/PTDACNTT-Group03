package com.example.English.teaching.center.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.English.teaching.center.model.Category;
import com.example.English.teaching.center.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }
}
