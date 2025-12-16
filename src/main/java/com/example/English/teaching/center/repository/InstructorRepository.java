package com.example.English.teaching.center.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.English.teaching.center.model.Instructor;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long>{
    List<Instructor> findByFullNameContainingOrExpertiseContaining(String name, String expertise);
}