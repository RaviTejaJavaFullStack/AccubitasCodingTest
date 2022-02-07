package com.accubits.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.accubits.model.Product;
import com.accubits.model.ProductDto;

public interface ProductService {

	ProductDto saveProduct(ProductDto productDto, MultipartFile file, String path);

	List<ProductDto> findAll(String contextPath);

	ProductDto findById(Long id, String contextPath);

	ProductDto updateProduct(ProductDto productDto, MultipartFile file, String contextPath);

	void deleteProduct(Long id);

	Product findById(long id);

	void updateProducts(Product p);

}
