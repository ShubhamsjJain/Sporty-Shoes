package com.sheryians.major.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sheryians.major.model.Category;
import com.sheryians.major.repository.categoryRepository;

@Service
public class categoryService {

	@Autowired
	categoryRepository categoryRepo;

	
	
	public void addCategory(Category cat) {
		categoryRepo.save(cat);
		
	}
	
	public List<Category> getAllCategories(){
		
		return categoryRepo.findAll();
	}
}
