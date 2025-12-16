package com.example.English.teaching.center.service;

import com.example.English.teaching.center.model.*;
import com.example.English.teaching.center.model.dto.SearchResultDTO;
import com.example.English.teaching.center.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {
    private final CourseRepository courseRepo;
    private final InstructorRepository instructorRepo;
    private final MaterialRepository materialRepo;
    private final PostRepository postRepo;

    public SearchService(CourseRepository courseRepository,
                        InstructorRepository instructorRepository,
                        MaterialRepository materialRepository,
                        PostRepository postRepository
    ){
        this.courseRepo = courseRepository;
        this.instructorRepo = instructorRepository;
        this.materialRepo = materialRepository;
        this.postRepo = postRepository;
    }

    public List<SearchResultDTO> searchEverything(String keyword) {
        List<SearchResultDTO> results = new ArrayList<>();
        String kw = keyword.trim();

        // 1. TÌM KHÓA HỌC
        List<Course> courses = courseRepo.searchFlexible(keyword.trim());
        for (Course c : courses) {
            
            String categoryParam = "IELTS"; 
            
            Category cat = c.getCategory(); 
            
            if (cat != null) {
                String nameCheck = cat.getName().toUpperCase(); 
                String slugCheck = cat.getSlug().toUpperCase();

                if (nameCheck.contains("IELTS") || slugCheck.contains("IELTS")) {
                    categoryParam = "IELTS";
                } 
                else if (nameCheck.contains("GIAO") || nameCheck.contains("COMMUNICATE") || slugCheck.contains("COMMUNICATE")) {
                    categoryParam = "COMMUNICATE";
                } 
                else if (nameCheck.contains("TRẺ") || nameCheck.contains("CHILDREN") || slugCheck.contains("CHILDREN")) {
                    categoryParam = "CHILDREN";
                }
            }

            String finalUrl = "/user/course?category=" + categoryParam;

            results.add(SearchResultDTO.builder()
                    .title(c.getTitle())
                    .description("Học phí: " + String.format("%,.0f đ", c.getPrice()))
                    .image(c.getImageUrl())
                    .url(finalUrl) 
                    .type("COURSE")
                    .typeLabel("Khóa học")
                    .build());
        }

        // 2. TÌM GIẢNG VIÊN
        List<Instructor> instructors = instructorRepo.findByFullNameContainingOrExpertiseContaining(kw, kw);
        for (Instructor i : instructors) {
            results.add(SearchResultDTO.builder()
                    .title(i.getFullName())
                    .description("Chuyên môn: " + i.getExpertise())
                    .image(i.getAvatarUrl())
                    .url("/user/i-teacher") 
                    .type("INSTRUCTOR")
                    .typeLabel("Giảng viên")
                    .build());
        }

        // 3. TÌM TÀI LIỆU
        List<Material> materials = materialRepo.findByTitleContainingOrTopicGroupContaining(kw, kw);
        for (Material m : materials) {
            results.add(SearchResultDTO.builder()
                    .title(m.getTitle())
                    .description("Chủ đề: " + m.getTopicGroup())
                    .image(m.getThumbnailUrl())
                    .url(m.getFileUrl()) 
                    .type("MATERIAL")
                    .typeLabel("Tài liệu")
                    .build());
        }

        // 4. TÌM TIN TỨC
        List<Post> posts = postRepo.findByTitleContaining(kw);
        for (Post p : posts) {
            results.add(SearchResultDTO.builder()
                    .title(p.getTitle())
                    .description("Ngày đăng: " + p.getPublishedAt().toLocalDate())
                    .image(p.getThumbnailUrl())
                    .url("/user/blog/" + p.getSlug())
                    .type("POST")
                    .typeLabel("Tin tức")
                    .build());
        }

        return results;
    }
}