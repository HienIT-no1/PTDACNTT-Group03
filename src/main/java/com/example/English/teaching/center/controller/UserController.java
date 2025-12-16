package com.example.English.teaching.center.controller;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.English.teaching.center.model.Instructor;
import com.example.English.teaching.center.model.Post;
import com.example.English.teaching.center.repository.InstructorRepository;
import com.example.English.teaching.center.service.PostService;

@Controller
public class UserController {

    private final PostService postService;
    private final InstructorRepository instructorRepository;

    public UserController(PostService postService,
                        InstructorRepository instructorRepository
    ) {
        this.postService = postService;
        this.instructorRepository = instructorRepository;
    }

    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/user/home";
    }

    @GetMapping("/user/home")
    public String userHome() {
        return "user/home";
    }

    @GetMapping("/user/about")
    public String userAbout(){
        return "user/about";
    }

    @GetMapping("/user/i-teacher")
    public String userITeacher(Model model) {
        List<Instructor> teachers = instructorRepository.findAll();

        model.addAttribute("teachers", teachers);
        return "user/i-teacher";
    }

    @GetMapping("/user/student-feelings")
    public String studentFeelings(){
        return "user/student-feelings";
    }

    @GetMapping("/user/new-events")
    public String getNewEventsPage(Model model) {
        List<Post> newsList = postService.getNewPosts();
        model.addAttribute("newsList", newsList);

        return "user/new-events";
    }

    @GetMapping("/user/new-events/{slug}")
    public String getPostDetail(@PathVariable String slug, Model model) {
        try {
            Post post = postService.getPostBySlug(slug);
            
            List<Post> hotPosts = postService.getNewPosts(); 
            
            model.addAttribute("post", post);
            model.addAttribute("hotPosts", hotPosts); 
            
            return "user/new-events-details";
            
        } catch (RuntimeException e) {
            System.out.println("Lỗi: " + e.getMessage()); 
            return "redirect:/user/new-events"; 
        }
    }

    @GetMapping("/user/blog")
    public String getNewBlogPage(Model model) {
        List<Post> newsList = postService.getNewPostBlogs();
        model.addAttribute("newsList", newsList);

        return "user/blog";
    }

    @GetMapping("/user/blog/{slug}")
    public String getBlogDetail(@PathVariable String slug, Model model) {
        try {
            Post post = postService.getPostBySlug(slug);
            
            List<Post> hotPosts = postService.getNewPostBlogs(); 
            
            model.addAttribute("post", post);
            model.addAttribute("hotPosts", hotPosts); 
            
            return "user/blog-details";
            
        } catch (RuntimeException e) {
            System.out.println("Lỗi: " + e.getMessage()); 
            return "redirect:/user/blog"; 
        }
    }
}