package com.example.English.teaching.center.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.English.teaching.center.model.Category;
import com.example.English.teaching.center.model.Course;
import com.example.English.teaching.center.model.Instructor;
import com.example.English.teaching.center.model.Material;
import com.example.English.teaching.center.model.Post;
import com.example.English.teaching.center.service.CategoryService;
import com.example.English.teaching.center.service.CourseService;
import com.example.English.teaching.center.service.InstructorService;
import com.example.English.teaching.center.service.MaterialService;
import com.example.English.teaching.center.service.PostService;

import jakarta.persistence.Column;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final CourseService courseService;
    private final CategoryService categoryService;
    private final InstructorService instructorService;
    private final PostService postService;
    private final MaterialService materialService;

    public AdminController(CourseService courseService,
            CategoryService categoryService,
            InstructorService instructorService,
            PostService postService,
            MaterialService materialService){
        this.courseService = courseService;
        this.categoryService = categoryService;
        this.instructorService = instructorService;
        this.postService = postService;
        this.materialService = materialService;
    }

    @GetMapping("/login")
    public String showLoginForm( @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "message", required = false) String message,
            @RequestParam(value = "logout", required = false) String logout,
            Model model){

        model.addAttribute("loginError", error != null);
        model.addAttribute("errorMessage",
            message != null ? message : (error != null ? "Email hoặc mật khẩu không đúng" : null));
        model.addAttribute("logoutMessage", logout != null ? "Bạn đã đăng xuất thành công!" : null);

        return "admin/login";
    }

    @GetMapping("/dashboard")
    public String dashboardCourses(Model model) {
        model.addAttribute("listInstructors", instructorService.getAllIntructors());
        model.addAttribute("listCategories", categoryService.getAllCategories());
        model.addAttribute("listCourses", courseService.getAllCourses());

        model.addAttribute("newCourse", new Course());

        model.addAttribute("pageTitle", "Quản lý Khóa học");
        model.addAttribute("activeTab", "courses"); // Để đánh dấu menu active
        return "admin/dashboard"; 
    }

    @PostMapping("/courses/save")
    public String saveCourse(@ModelAttribute("newcourse") Course course,
            @RequestParam(value = "categoryId", required = false) Long categoryId,
            @RequestParam(value = "instructorId", required = false) Long instructorId) {

        if (categoryId != null) {
            Category cat = categoryService.getAllCategories().stream()
                .filter(c -> c.getId().equals(categoryId)).findFirst().orElse(null);
            course.setCategory(cat);
        }
            
        if (instructorId != null) {
            Instructor ins = instructorService.getAllIntructors().stream()
                .filter(i -> i.getId().equals(instructorId)).findFirst().orElse(null);
            course.setInstructor(ins);
        }
        courseService.saveCourse(course);

        return "redirect:/admin/dashboard";
    }

    @GetMapping("/courses/delete/{id}")
    public String deleteCourse(@PathVariable(value = "id") Long id) {
        courseService.deleteCourseById(id);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/news")
    public String dashboardNews(Model model) {
        model.addAttribute("listPosts", postService.getAllPosts());
        model.addAttribute("pageTitle", "Tin tức & Sự kiện");
        model.addAttribute("activeTab", "news");
        return "admin/news";
    }

    @PostMapping("/news/save")
    public String savePost(@ModelAttribute("post") Post post) {
        if (post.getId() != null) {
            Post existingPost = postService.getPostById(post.getId()).orElse(null);
            if (existingPost != null) {
                // Cập nhật các thông tin mới
                existingPost.setTitle(post.getTitle());
                existingPost.setSlug(post.getSlug());
                existingPost.setContent(post.getContent());
                existingPost.setThumbnailUrl(post.getThumbnailUrl());
                existingPost.setType(post.getType());
                
                postService.savePost(existingPost);
            }
        } else {
            postService.savePost(post);
        }

        return "redirect:/admin/news";
    }

    @GetMapping("/news/delete/{id}")
    public String deleteNew(@PathVariable(value = "id") Long id){
        postService.deletePostById(id);
        return "redirect:/admin/news";
    }

    @GetMapping("/materials")
    public String dashboardMaterials(Model model) {
        model.addAttribute("listMaterials", materialService.getAllMaterials());
        model.addAttribute("pageTitle", "Kho Tài liệu");
        model.addAttribute("activeTab", "materials");
        return "admin/materials";
    }

    @PostMapping("/materials/save")
    public String saveMaterial(@ModelAttribute("material") Material material) {
        if (material.getId() != null) {
            Material existingMaterial = materialService.getMaterialById(material.getId()).orElse(null);
            if (existingMaterial != null) {
                existingMaterial.setTitle(material.getTitle());
                existingMaterial.setDescription(material.getDescription());
                existingMaterial.setType(material.getType());
                existingMaterial.setFileUrl(material.getFileUrl());
                existingMaterial.setThumbnailUrl(material.getThumbnailUrl());
                
                materialService.saveMaterial(existingMaterial);
            }
        } else {
            materialService.saveMaterial(material);
        }

        return "redirect:/admin/materials";
    }

    @GetMapping("/materials/delete/{id}")
    public String deleteMaterial(@PathVariable(value = "id") Long id){
        materialService.deleteMaterialById(id);
        return "redirect:/admin/materials";
    }
}
