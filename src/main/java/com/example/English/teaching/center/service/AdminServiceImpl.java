package com.example.English.teaching.center.service;

import java.util.Optional;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.English.teaching.center.model.Admin;
import com.example.English.teaching.center.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService{

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminServiceImpl(AdminRepository adminRepository, PasswordEncoder passwordEncoder){
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Admin login(String username, String rawPassword) throws IllegalAccessException {
        Optional<Admin> optionalAdmin = adminRepository.findByUsername(username);

        if(!optionalAdmin.isPresent()){
            throw new BadCredentialsException("Tài khoản không chính xác");
        }

        Admin admin = optionalAdmin.get();
        if(!passwordEncoder.matches(rawPassword, admin.getPassword())){
            throw new BadCredentialsException("Mật khẩu không đưng");
        }

        return admin;
    }
    
}
