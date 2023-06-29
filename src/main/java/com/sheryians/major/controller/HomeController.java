package com.sheryians.major.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sheryians.major.global.GlobalData;
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
		
		model.addAttribute("cartCount", GlobalData.cart.size());
		return "index";
	}
	
	@GetMapping({"/shop"})
	public String shop(Model model) {
		model.addAttribute("categories", catService.getAllCategories());
		model.addAttribute("products", proService.getAllProducts());
		model.addAttribute("cartCount", GlobalData.cart.size());
		return "shop";
	}
	
	@GetMapping({"/shop/category/{id}"})
	public String shopByCategory(Model model, @PathVariable int id) {
		model.addAttribute("categories", catService.getAllCategories());
		model.addAttribute("products", proService.getAllProductsByCategoryId(id));
		model.addAttribute("cartCount", GlobalData.cart.size());
		return "shop";
	}
	
	@GetMapping({"/shop/viewproduct/{id}"})
	public String viewProduct(Model model, @PathVariable int id) {
		model.addAttribute("product", proService.getProductById(id).get());
		model.addAttribute("cartCount", GlobalData.cart.size());
		return "viewProduct";
	}
	
	
	
	

}
