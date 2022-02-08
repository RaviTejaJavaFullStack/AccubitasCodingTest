package com.accubits.dao.integration.testcases;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.accubits.dao.CategoryDao;
import com.accubits.model.Category;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryDaoTestCases {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Test
	public void testSaveCategory() {
		Category category = new Category();
		category.setCategoryName("Homeo Medicines");
		category.setProducts(null);
		Category saveInDb = entityManager.persist(category);
		Category getFromDb = categoryDao.findById(saveInDb.getId()).get();
		assertThat(getFromDb).isEqualTo(saveInDb);
	}
}
