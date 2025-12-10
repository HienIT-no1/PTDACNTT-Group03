package com.example.English.teaching.center.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.English.teaching.center.model.MaterialCategory;
import com.example.English.teaching.center.repository.MaterialCategoryRepository;

@Service
public class MaterialCategoryServiceImp implements MaterialCategoryService{
    private final MaterialCategoryRepository materialCategoryRepository;

    public MaterialCategoryServiceImp(
        MaterialCategoryRepository materialCategoryRepository
    ){
        this.materialCategoryRepository = materialCategoryRepository;
    }

    @Override
    public List<MaterialCategory> getAllCategories() {
        return materialCategoryRepository.findAll();
    }
    
}
