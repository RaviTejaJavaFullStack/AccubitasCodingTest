package com.accubits.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accubits.dao.CartItemDao;
import com.accubits.dao.CustomerOrderDao;
import com.accubits.dao.UserDao;
import com.accubits.model.CartItem;
import com.accubits.model.CustomerOrder;
import com.accubits.model.User;
import com.accubits.service.CartItemService;

@Service(value="cartItemService")
public class CartItemServiceImpl implements CartItemService {

	@Autowired
	private CartItemDao cartItemDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CustomerOrderDao customerOrderDao;
	
	@Override
	public List<CartItem> getCart(String username) {
		User user = userDao.findByUsername(username);
		return cartItemDao.findByUser(user);
	}

	@Override
	public void addToCart(CartItem cartItem) {
		cartItemDao.save(cartItem);
	}

	@Override
	public void removeCartItem(long cartId) {
		cartItemDao.deleteById(cartId);
	}

	@Override
	public CustomerOrder createCustomerOrder(CustomerOrder customerOrder) {
		return customerOrderDao.save(customerOrder);
	}

}
