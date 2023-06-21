package com.sheryians.major.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.sheryians.major.model.Category;
import com.sheryians.major.service.categoryService;

@Controller
public class AdminController {
	
	@Autowired
	categoryService catService;

	@GetMapping("/admin")
	public String adminHome() {
		
		return "adminHome";
	}
	
	@GetMapping("/admin/categories")
	public String getCategories(Model model) {
		model.addAttribute("categories", catService.getAllCategories());
		return "categories";
	}
	
	@GetMapping("/admin/categories/add")
	public String addCategoriesGet(Model model) {
		
		model.addAttribute("category", new Category());
		return "categoriesAdd";
	}
	
	@PostMapping("/admin/categories/add")
	public String addCategoriesPost(@ModelAttribute("category") Category category) {
		
		catService.addCategory(category);
		return "redirect:/admin/categories";
	}
	
}
