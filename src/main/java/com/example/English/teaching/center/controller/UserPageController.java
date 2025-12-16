package com.example.English.teaching.center.controller;

import com.example.English.teaching.center.model.dto.SearchResultDTO;
import com.example.English.teaching.center.service.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserPageController {
    private final SearchService searchService; 

    public UserPageController(SearchService searchService){
        this.searchService = searchService;
    }

    @GetMapping("/user/search")
    public String searchPage(@RequestParam(value = "keyword", required = false) String keyword, Model model) {

        if (keyword == null || keyword.trim().isEmpty()) {
            return "redirect:/user/home"; 
        }
        List<SearchResultDTO> results = searchService.searchEverything(keyword);

        model.addAttribute("results", results);
        model.addAttribute("keyword", keyword);
        model.addAttribute("resultCount", results.size());

        return "user/search-result"; 
    }
}