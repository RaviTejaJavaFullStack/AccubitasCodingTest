package com.accubits.model;

import org.springframework.web.multipart.MultipartFile;

public class ProductDto {

	private long id;
	
	private String name;

	private String code;

	private double price;

	private Category category;
	
	private MultipartFile image;
	
	private int quantity;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "ProductDto [id=" + id + ", name=" + name + ", code=" + code + ", price=" + price + ", category="
				+ category + ", image=" + image + ", quantity=" + quantity + "]";
	}

}
