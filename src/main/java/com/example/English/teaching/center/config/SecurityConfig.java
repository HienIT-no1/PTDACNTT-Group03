package com.example.English.teaching.center.config; // (Nhớ đổi tên package cho đúng)

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                // (THÊM MỚI) Cho phép tất cả mọi người truy cập trang chủ
                .requestMatchers("/").permitAll() 
                // Cho phép tất cả mọi người truy cập vào các đường dẫn bắt đầu bằng /user/
                .requestMatchers("/user/**").permitAll() 
                // Cho phép truy cập các thư mục css, js, images
                .requestMatchers("/css/**", "/js/**", "/images/**").permitAll() 
                // Cho phép tất cả mọi người truy cập trang login
                .requestMatchers("/login").permitAll() 
                // Tất cả các yêu cầu khác đều phải xác thực (đăng nhập)
                .anyRequest().authenticated() 
            )
            .formLogin(form -> form // Cấu hình trang đăng nhập
                .loginPage("/login") // Chỉ định trang login tùy chỉnh
                .permitAll()
            )
            .logout(logout -> logout // Cấu hình đăng xuất
                .logoutUrl("/logout")
                .permitAll()
            );

        return http.build();
    }
}