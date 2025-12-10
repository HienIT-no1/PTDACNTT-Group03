package com.example.English.teaching.center.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.English.teaching.center.model.Material;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long>{
   
    Page<Material> findAllByOrderByCreatedAtDesc(Pageable pageable);

    Page<Material> findByMaterialCategory_Slug(String slug, Pageable pageable);

    Page<Material> findByMaterialCategory_SlugAndTopicGroup(String slug, String topicGroup, Pageable pageable);

    Page<Material> findByTopicGroup(String topicGroup, Pageable pageable);
}
