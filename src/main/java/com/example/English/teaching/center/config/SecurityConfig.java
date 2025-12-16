package com.example.English.teaching.center.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.English.teaching.center.security.CustomAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
        @Lazy CustomAuthenticationProvider customAuthProvider
    ) throws Exception {
        http
            .csrf(csrf -> csrf.disable())    

            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                .requestMatchers("/", "/user/**").permitAll()
                .requestMatchers("/admin/login").permitAll()
                .requestMatchers("/api/consultation/**").permitAll()
                .requestMatchers("/admin/**").authenticated() 
                
                .anyRequest().authenticated() 
            )
            .authenticationProvider(customAuthProvider)
            .formLogin(form -> form 
                .loginPage("/admin/login")
                .loginProcessingUrl("/process-login")
                .successHandler(successHandler())
                .failureUrl("/admin/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                .logoutSuccessUrl("/admin/login?logout")
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler successHandler(){
        return (request, response, authentication) -> {
            String redirectUrl = "/admin/dashboard"; 
            response.sendRedirect(redirectUrl);
        };
    }
}