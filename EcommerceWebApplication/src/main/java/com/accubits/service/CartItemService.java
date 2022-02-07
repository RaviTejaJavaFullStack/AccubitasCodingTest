package com.accubits.service;

import java.util.List;

import com.accubits.model.CartItem;
import com.accubits.model.CustomerOrder;

public interface CartItemService {

	List<CartItem> getCart(String username);

	void addToCart(CartItem cartItem);

	void removeCartItem(long cartId);

	CustomerOrder createCustomerOrder(CustomerOrder customerOrder);

}
