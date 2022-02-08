package com.accubits.service.integration.testcases;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.accubits.dao.UserDao;
import com.accubits.model.Role;
import com.accubits.model.User;
import com.accubits.model.UserDto;
import com.accubits.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@Autowired
	private UserService userService;
	
	@MockBean
	private UserDao userDao;
	
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	
	@Test
	public void testSaveUser() {
		try {
			User user = new User();
			user.setId(1);
			user.setUsername("Mahadev");
			user.setPassword(bcryptEncoder.encode("Mahadev@1234"));
			Set<Role> roles = new HashSet<>();
			Role e = new Role();
			e.setId(1);
			e.setName("ADMIN");
			e.setDescription("Admin role");
			roles.add(e);
			user.setRoles(roles);
			user.setPassword(bcryptEncoder.encode(user.getPassword()));
			Mockito.when(userDao.save(user)).thenReturn(user);
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(user, userDto);
			List<String> list = new ArrayList<>();
			list.add("ADMIN");
			userDto.setUserRole(list);
			assertThat(userService.save(userDto)).isEqualTo(user);
		} catch (BeansException e) {
			e.printStackTrace();
		}
	}
	
	
}
