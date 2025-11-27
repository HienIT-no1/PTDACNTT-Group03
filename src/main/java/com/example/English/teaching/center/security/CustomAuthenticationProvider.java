package com.example.English.teaching.center.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.example.English.teaching.center.model.Admin;
import com.example.English.teaching.center.service.AdminService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final AdminService adminService;

    public CustomAuthenticationProvider(AdminService adminService){
        this.adminService = adminService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();


        Admin user = null;

        try{
            user = adminService.login(username, password);
        }catch(IllegalAccessException e){
            throw new BadCredentialsException(e.getMessage());
        }

        List<GrantedAuthority> authorities = new ArrayList<>();

        try{
            Admin admin = adminService.login(username, password);
            return new UsernamePasswordAuthenticationToken(
                admin.getUsername(),
                admin.getPassword(),
                authorities
            );
        }catch(IllegalAccessException e){
            throw new BadCredentialsException(e.getMessage());
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
    
}
