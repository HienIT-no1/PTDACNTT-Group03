package com.example.English.teaching.center.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.English.teaching.center.model.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Long>{

}