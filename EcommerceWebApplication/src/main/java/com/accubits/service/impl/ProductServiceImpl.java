package com.accubits.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.accubits.dao.ProductDao;
import com.accubits.model.Category;
import com.accubits.model.Product;
import com.accubits.model.ProductDto;
import com.accubits.service.CategoryService;
import com.accubits.service.ProductService;

@Service(value = "productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private CategoryService categoryService;

	@Override
	public ProductDto saveProduct(ProductDto productDto,MultipartFile file,String contextPath) {
		try {
			Product product = new Product();
			BeanUtils.copyProperties(productDto, product);
			Byte[] byteObjects = new Byte[file.getBytes().length];
			int i = 0;
			for (byte b : file.getBytes()) {
				byteObjects[i++] = b;
			}
			product.setDbImage(byteObjects);
			Category category = categoryService.findById(productDto.getCategory().getId());
			product.setCategory(category);
			Product savedProduct = productDao.save(product);
			Path path=Paths.get(contextPath+"/WEB-INF/resources/images/"+savedProduct.getId()+".jpg");
			copyFile(path,file);
			productDto.setId(savedProduct.getId());
			productDto.setCategory(category);
			return productDto;
		} catch (BeansException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void copyFile(Path path,MultipartFile img) {
		if(!img.isEmpty()){
			try {
				img.transferTo(new File(path.toString()));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public List<ProductDto> findAll(String contextPath) {
		List<Product> list = new ArrayList<>();
		productDao.findAll().iterator().forEachRemaining(list::add);
		return list.stream().map(e->this.convertToDto(e,contextPath)).collect(Collectors.toList());
	}

	private ProductDto convertToDto(Product product,String contextPath) {
		ProductDto productDto = new ProductDto();
		productDto.setId(product.getId());
		productDto.setCode(product.getCode());
		productDto.setPrice(product.getPrice());
		productDto.setName(product.getName());
		Category category = categoryService.findById(product.getCategory().getId());
		productDto.setCategory(category);
		productDto.setImage(fileConvertions(Paths.get(contextPath+"/WEB-INF/resources/images/"+product.getId()+".jpg"),product.getId()+".jpg"));
		return productDto;
	}
	
	private MultipartFile fileConvertions(Path path,String name) {
		String contentType = "img/jpeg";
		byte[] content = null;
		try {
		    content = Files.readAllBytes(path);
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return new MockMultipartFile(name,name, contentType, content);
	}
	
	@Override
	public ProductDto findById(Long id,String contextPath) {
		Product product = productDao.findById(id).isPresent() ? productDao.findById(id).get() : null;
		if(product != null) {
			return convertToDto(product,contextPath);		
		}else {
			return null;
		}
	}

	@Override
	public ProductDto updateProduct(ProductDto productDto, MultipartFile file,String contextPath) {
		try {
			Product product = productDao.findById(productDto.getId()).isPresent() ? productDao.findById(productDto.getId()).get() : null;
			if(product != null) {
				BeanUtils.copyProperties(productDto, product);
				Byte[] byteObjects = new Byte[file.getBytes().length];
				int i = 0;
				for (byte b : file.getBytes()) {
					byteObjects[i++] = b;
				}
				product.setDbImage(byteObjects);
				Category category = categoryService.findById(productDto.getCategory().getId());
				product.setCategory(category);
				Product savedProduct = productDao.save(product);
				Path path=Paths.get(contextPath+"/WEB-INF/resources/images/"+savedProduct.getId()+".jpg");
				copyFile(path,file);
				productDto.setId(savedProduct.getId());
				productDto.setCategory(category);
				return productDto;
			}else {
				return productDto;
			}
		} catch (BeansException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteProduct(Long id) {
		productDao.deleteById(id);
	}

	@Override
	public Product findById(long id) {
		return productDao.findById(id).isPresent() ? productDao.findById(id).get() : null;
	}

	@Override
	public void updateProducts(Product productDto) {
		try {
			Product dbProduct = productDao.findById(productDto.getId()).isPresent() ? productDao.findById(productDto.getId()).get() : null;
			if(dbProduct != null) {
				BeanUtils.copyProperties(productDto, dbProduct);
				productDao.save(dbProduct);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
