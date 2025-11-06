package com.example.English.teaching.center.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/user/Home";
    }

    // User Dashboard------------------------------------------------------------
    @GetMapping("/user/Home")
    public String userHome() {
        return "user/Home";
    }

    @GetMapping("/user/Blog")
    public String userBlog() {
        return "user/Blog";
    }

    @GetMapping("/user/Courses")
    public String userCourses() {
        return "user/Courses";
    }

    @GetMapping("/user/CourseDetails")
    public String userCourseDetails() {
        return "user/CourseDetails";
    }

    @GetMapping("/user/Document")
    public String userDocument() {
        return "user/Document";
    }

    @GetMapping("/user/ITeacher")
    public String userITeacher() {
        return "user/ITeacher";
    }

    @GetMapping("/user/NewEvents")
    public String userNewEvents() {
        return "user/NewEvents";
    }
}