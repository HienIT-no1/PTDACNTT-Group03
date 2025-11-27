package com.example.English.teaching.center.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.English.teaching.center.model.Material;

public interface MaterialRepository extends JpaRepository<Material, Long>{
    
}
