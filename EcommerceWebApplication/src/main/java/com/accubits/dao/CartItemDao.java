package com.accubits.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.accubits.model.CartItem;
import com.accubits.model.User;

@Transactional
@Repository
public interface CartItemDao extends CrudRepository<CartItem, Long> {

	List<CartItem> findByUser(User user);

}
