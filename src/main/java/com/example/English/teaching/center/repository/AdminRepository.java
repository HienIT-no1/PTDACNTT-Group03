package com.example.English.teaching.center.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.English.teaching.center.model.Admin;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long>{
    Optional<Admin> findByUsername(String username);
}
