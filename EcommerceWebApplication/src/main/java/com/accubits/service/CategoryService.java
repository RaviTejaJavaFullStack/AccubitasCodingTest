package com.accubits.service;

import java.util.List;

import com.accubits.model.Category;

public interface CategoryService {

	Category saveCategory(Category category);

	Category findById(long id);

	Category updateCategory(Category category);

	void deleteCategory(Long id);

	List<Category> findAll();

}
