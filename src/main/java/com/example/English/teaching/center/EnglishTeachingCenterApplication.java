package com.example.English.teaching.center;
 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class EnglishTeachingCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnglishTeachingCenterApplication.class, args);

		// BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // String rawPassword = "@Nhom3PTDA"; 
        // String encodedPassword = encoder.encode(rawPassword);
        // System.out.println("Password:"+ encodedPassword);
	}
}
