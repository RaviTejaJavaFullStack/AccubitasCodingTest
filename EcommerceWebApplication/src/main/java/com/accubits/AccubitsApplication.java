package com.accubits;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.accubits.dao.CategoryDao;
import com.accubits.dao.RoleDao;
import com.accubits.model.Category;
import com.accubits.model.Role;

@SpringBootApplication
@Transactional
public class AccubitsApplication implements ApplicationRunner{

	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
    public static void main(String[] args) {
        SpringApplication.run(AccubitsApplication.class, args);
    }

	@Override
	public void run(ApplicationArguments args) throws Exception {
		if(roleDao.count() == 0) {
			addRoles();	
		}
		if(categoryDao.count() == 0) {
			addCategories();
		}
	}

	public void addCategories() {
		try {
			Category category = new Category();
			category.setCategoryName("ELECTRONICS");
			categoryDao.save(category);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void addRoles() {
		try {
			List<Role> roles = new ArrayList<>();
			Role role = new Role();
			role.setDescription("Admin role");
			role.setName("ADMIN");
			roles.add(role);
			Role role1 = new Role();
			role1.setDescription("User role");
			role1.setName("USER");
			roles.add(role1);
			roleDao.saveAll(roles);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
