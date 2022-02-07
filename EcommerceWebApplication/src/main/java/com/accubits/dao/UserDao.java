package com.accubits.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.accubits.model.User;

@Transactional
@Repository
public interface UserDao extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
