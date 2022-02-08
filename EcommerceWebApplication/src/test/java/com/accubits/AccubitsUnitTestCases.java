package com.accubits;

import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.accubits.model.User;
import com.accubits.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccubitsUnitTestCases {

	@Autowired
	UserService userService;
	
	@Test
	public void getAllUsers() {
		List<User> users =  userService.findAll();
		System.out.println("users"+users);
		assertNotEquals(users.size(), 0);
	}
}
