package com.example.English.teaching.center.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/user/home";
    }

    // User Dashboard------------------------------------------------------------
    @GetMapping("/user/home")
    public String userHome() {
        return "user/home";
    }

    @GetMapping("/user/blog")
    public String userBlog() {
        return "user/blog";
    }

    @GetMapping("/user/courses")
    public String userCourses() {
        return "user/courses";
    }

    @GetMapping("/user/course-details")
    public String userCourseDetails() {
        return "user/course-details";
    }

    @GetMapping("/user/document")
    public String userDocument() {
        return "user/document";
    }

    @GetMapping("/user/about")
    public String userAbout(){
        return "user/about";
    }

    @GetMapping("/user/i-teacher")
    public String userITeacher() {
        return "user/i-teacher";
    }

    @GetMapping("/user/new-events")
    public String userNewEvents() {
        return "user/new-events";
    }

    @GetMapping("/user/new-events-details01")
    public String userNewEventsDetails01() {
        return "user/new-events-details01";
    }
}