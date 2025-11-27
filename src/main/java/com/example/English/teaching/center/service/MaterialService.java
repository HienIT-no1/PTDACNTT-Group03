package com.example.English.teaching.center.service;

import java.util.List;
import java.util.Optional;

import com.example.English.teaching.center.model.Material;

public interface MaterialService {

    List<Material> getAllMaterials();

    void deleteMaterialById(Long id);

    void saveMaterial(Material material);

    Optional<Material> getMaterialById(Long id);
}
