package com.example.English.teaching.center.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.English.teaching.center.model.Material;
import com.example.English.teaching.center.repository.MaterialRepository;

@Service
public class MaterialServiceImpl implements MaterialService{

    private final MaterialRepository materialRepository;

    public MaterialServiceImpl(MaterialRepository materialRepository){
        this.materialRepository = materialRepository;
    }

    @Override
    public List<Material> getAllMaterials() {
        return materialRepository.findAll();
    }

    @Override
    public void deleteMaterialById(Long id) {
        materialRepository.deleteById(id);
    }

    @Override
    public void saveMaterial(Material material) {
        materialRepository.save(material);
    }

    @Override
    public Optional<Material> getMaterialById(Long id) {
        return materialRepository.findById(id);
    }
    
}
