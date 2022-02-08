package com.accubits.service.integration.testcases;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.accubits.dao.CategoryDao;
import com.accubits.model.Category;
import com.accubits.service.CategoryService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceTest {

	@Autowired
	private CategoryService categoryService;
	
	@MockBean
	private CategoryDao categoryDao;
	
	@Test
	public void testSaveCategory() {
		Category category = new Category();
		category.setId(2);
		category.setCategoryName("Medicines");
		category.setProducts(null);
//		CategoryDao mock = org.mockito.Mockito.mock(CategoryDao.class);
		Mockito.when(categoryDao.save(category)).thenReturn(category);
		assertThat(categoryService.saveCategory(category)).isEqualTo(category);
	}
}
