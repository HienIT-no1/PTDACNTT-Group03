package com.example.English.teaching.center.service;

import com.example.English.teaching.center.model.Admin;

public interface AdminService {
    Admin login(String username, String rawPassword) throws IllegalAccessException;
}
