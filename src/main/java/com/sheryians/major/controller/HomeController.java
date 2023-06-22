package com.sheryians.major.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sheryians.major.service.categoryService;
import com.sheryians.major.service.productService;

@Controller
public class HomeController {
	
	@Autowired
	categoryService catService;
	
	@Autowired
	productService proService;
	
	@GetMapping({"/", "/home"})
	public String home(Model model) {
		return "index";
	}
	
	@GetMapping({"/shop"})
	public String shop(Model model) {
		model.addAttribute("categories", catService.getAllCategories());
		model.addAttribute("products", proService.getAllProducts());
		return "shop";
	}

}
