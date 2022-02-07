package com.accubits.service;

import java.util.List;

import com.accubits.model.User;
import com.accubits.model.UserDto;

public interface UserService {

	User save(UserDto user);

	List<User> findAll();

	void delete(long id);

	User findOne(String username);

	User findById(Long id);

}
