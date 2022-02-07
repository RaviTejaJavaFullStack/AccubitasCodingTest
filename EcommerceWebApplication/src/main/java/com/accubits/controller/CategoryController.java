package com.accubits.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accubits.model.Category;
import com.accubits.service.CategoryService;

@RestController
@RequestMapping(value="/api/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/addCategory")
	public ResponseEntity<?> addCategory(@RequestBody Category category){
		Category savedCategory = categoryService.saveCategory(category);
		return new ResponseEntity<Category>(savedCategory, HttpStatus.CREATED);
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/updateCategory")
	public ResponseEntity<?> updateCategory(@RequestBody Category category){
		Category savedCategory = categoryService.updateCategory(category);
		return new ResponseEntity<Category>(savedCategory, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value="/getAllCategories")
    public ResponseEntity<?> getAllCategories(){
		try {
			List<Category> list = categoryService.findAll(); 
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<>(list, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(list, HttpStatus.NO_CONTENT);	
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/getCategory/{id}")
    public ResponseEntity<?> getCategory(@PathVariable(value = "id") Long id){
    	try {
    		Category category = categoryService.findById(id);
			if(category != null) {
				return new ResponseEntity<>(category, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(category, HttpStatus.NO_CONTENT);	
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/deleteCategory/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable(value = "id") Long id){
        try {
        	categoryService.deleteCategory(id);
			return new ResponseEntity<>("Product Deleted Successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Product Not Found", HttpStatus.NO_CONTENT);
		}
    }
}
