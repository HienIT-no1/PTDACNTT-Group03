package com.example.English.teaching.center.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.English.teaching.center.model.Test;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
    
}