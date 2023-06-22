package com.sheryians.major.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sheryians.major.model.Product;
import com.sheryians.major.repository.productRepository;

@Service
public class productService {
	
	private productRepository productRepo;
	
	
     public productService(productRepository productRepo) {
		super();
		this.productRepo = productRepo;
	 }


     public List<Product> getAllProducts(){
		
		return productRepo.findAll();
	}
     
     public void addProduct(Product p) {
    	 
    	 productRepo.save(p);
     }

     public void removeProductById(long id) {
    	 productRepo.deleteById(id);
     }
     
     public Optional<Product> getProductById(long id){
    	 return productRepo.findById(id); 
     }
     
     public List<Product> getAllProductsByCategoryId(int id){
    	 
    	 return productRepo.findAllByCategory_Id(id);
     }
}
