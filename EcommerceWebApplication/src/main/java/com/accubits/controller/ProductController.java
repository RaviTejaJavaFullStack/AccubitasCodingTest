package com.accubits.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.accubits.model.ProductDto;
import com.accubits.service.ProductService;

@RestController
@RequestMapping(value="/api/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping(value="/addProduct")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addProduct(@RequestPart(value="imagefile",required=true) MultipartFile file,@RequestPart ProductDto productDto,HttpServletRequest request){
		try {
			ProductDto product = null;
			if(productDto.getName() != null) {
				String path = request.getServletContext().getRealPath("/");
				product = productService.saveProduct(productDto,file,path);	
			}
			return new ResponseEntity<>(product, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(value="/updateProduct")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateProduct(@RequestPart(value="imagefile",required=true) MultipartFile file,@RequestPart ProductDto productDto,HttpServletRequest request){
		try {
			ProductDto product = null;
			if(productDto.getName() != null) {
				String contextPath = request.getServletContext().getRealPath("/");
				product = productService.updateProduct(productDto,file,contextPath);	
			}
			return new ResponseEntity<>(product, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value="/getAllProducts")
    public ResponseEntity<?> getAllProducts(HttpServletRequest request){
		try {
			String contextPath = request.getServletContext().getRealPath("/");
			List<ProductDto> list = productService.findAll(contextPath); 
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
    @GetMapping(value = "/getProduct/{id}")
    public ResponseEntity<?> getProduct(@PathVariable(value = "id") Long id,HttpServletRequest request){
    	try {
			String contextPath = request.getServletContext().getRealPath("/");
			ProductDto productDto = productService.findById(id,contextPath);
			if(productDto != null) {
				return new ResponseEntity<>(productDto, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(productDto, HttpStatus.NO_CONTENT);	
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/deleteProduct/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(value = "id") Long id){
        try {
			productService.deleteProduct(id);
			return new ResponseEntity<>("Product Deleted Successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Product Not Found", HttpStatus.NO_CONTENT);
		}
    }
}
