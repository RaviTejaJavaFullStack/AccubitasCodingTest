package com.accubits.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accubits.dao.CategoryDao;
import com.accubits.model.Category;
import com.accubits.service.CategoryService;

@Service(value="categoryService")
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	
	@Override
	public Category saveCategory(Category category) {
		return categoryDao.save(category);
	}

	@Override
	public Category findById(long id) {
		return categoryDao.findById(id).isPresent() ? categoryDao.findById(id).get() : null;
	}

	@Override
	public Category updateCategory(Category category) {
		try {
			Category savedCategory = categoryDao.findById(category.getId()).isPresent() ? categoryDao.findById(category.getId()).get() : null;
			if(savedCategory != null) {
				BeanUtils.copyProperties(category, savedCategory);
				savedCategory = categoryDao.save(savedCategory);
				return savedCategory;
			}else {
				return category;
			}
		} catch (BeansException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteCategory(Long id) {
		categoryDao.deleteById(id);
	}

	@Override
	public List<Category> findAll() {
		List<Category> list = new ArrayList<>();
		categoryDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

}
