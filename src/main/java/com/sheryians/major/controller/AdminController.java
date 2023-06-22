package com.sheryians.major.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sheryians.major.dto.productDTO;
import com.sheryians.major.model.Category;
import com.sheryians.major.model.Product;
import com.sheryians.major.service.categoryService;
import com.sheryians.major.service.productService;

@Controller
public class AdminController {
	
	public String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";
	
	@Autowired
	categoryService catService;
	
	@Autowired
	productService proService;

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
	
	@GetMapping("/admin/categories/delete/{id}")
	public String deleteCategory(@PathVariable int id) {
		catService.removeCategoryById(id);
		return "redirect:/admin/categories";
		
	}
	
	@GetMapping("/admin/categories/update/{id}")
	public String updateCategory(@PathVariable int id, Model model) {
		
		Optional<Category> category = catService.getCategoryById(id);
		if(category.isPresent()) {
			model.addAttribute("category", category.get());
			return "categoriesAdd";
		}else {
			return "404";
		}
		
	}
	
	
	//Product Section
	
	@GetMapping("/admin/products")
	public String listProducts(Model model) {
		
		model.addAttribute("products", proService.getAllProducts());
		return "products";
		
	}
	
	@GetMapping("/admin/products/add")
	public String addProductGet(Model model) {
		
		model.addAttribute("productDTO", new productDTO());
		model.addAttribute("categories", catService.getAllCategories());
		return "productsAdd";
		
	}
	
	@PostMapping("/admin/products/add")
	public String addProductForm(@ModelAttribute("productDTO") productDTO productDto,
			                     @RequestParam("productImage")MultipartFile file,
			                     @RequestParam("imgName")String imgName) throws IOException{
		
		Product product = new Product();
		product.setId(productDto.getId());
		product.setName(productDto.getName());
		product.setCategory(catService.getCategoryById(productDto.getCategoryId()).get());
		product.setPrice(productDto.getPrice());
		product.setSize(productDto.getSize());
		product.setDescription(productDto.getDescription());
		
		String imageUUID;
		if(!file.isEmpty()) {
			imageUUID = file.getOriginalFilename();
			Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
			Files.write(fileNameAndPath, file.getBytes());
		}else {
			imageUUID = imgName;
		}
		
		product.setImageName(imageUUID);
		proService.addProduct(product);
		
		
		return "redirect:/admin/products";
	}
	
	@GetMapping("/admin/product/delete/{id}")
	public String deleteProduct(@PathVariable long id) {
		proService.removeProductById(id);
		return "redirect:/admin/products";
	}
	
	@GetMapping("/admin/product/update/{id}")
	public String updateProduct(@PathVariable long id, Model model) {
		
		Product product = proService.getProductById(id).get();
		productDTO proDTO = new productDTO();
		proDTO.setId(product.getId());
		proDTO.setName(product.getName());
		proDTO.setCategoryId(product.getCategory().getId());
		proDTO.setPrice(product.getPrice());
		proDTO.setSize(product.getSize());
		proDTO.setDescription(product.getDescription());
		proDTO.setImageName(product.getImageName());
		
		model.addAttribute("categories", catService.getAllCategories());
		model.addAttribute("productDTO", proDTO); 
		
		return"productsAdd";
	}
	
}
