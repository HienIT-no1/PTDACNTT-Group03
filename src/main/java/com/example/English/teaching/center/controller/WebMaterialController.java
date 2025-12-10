package com.example.English.teaching.center.controller;


import com.example.English.teaching.center.model.Material;
import com.example.English.teaching.center.service.*;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class WebMaterialController {

    private final MaterialService materialService;
    private final MaterialCategoryService materialCategoryService; 
    private final CategoryService categoryService; 

    @GetMapping("/user/document")
    public String viewDocuments(Model model, 
            @RequestParam(name = "category", required = false) String categorySlug,
            @RequestParam(name = "topic", required = false) String topicGroup,
            @RequestParam(name = "page", defaultValue = "1") int page) {
        
        Page<Material> pageResult = materialService.getFilterMaterials(categorySlug, topicGroup, page, 6);
        model.addAttribute("listMaterials", pageResult.getContent());
        model.addAttribute("totalPages", pageResult.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("allMaterialsForSidebar", materialService.getAllMaterials());
        
        model.addAttribute("listMaterialCategories", materialCategoryService.getAllCategories());
        model.addAttribute("listTopicCategories", categoryService.getAllCategories());

        model.addAttribute("currentCategory", categorySlug);
        model.addAttribute("currentTopic", topicGroup);

        return "user/document"; 
    }
}
