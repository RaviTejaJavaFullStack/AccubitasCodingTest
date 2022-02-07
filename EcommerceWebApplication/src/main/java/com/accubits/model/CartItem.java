package com.accubits.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@DynamicUpdate
public class CartItem {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long cartId;
	
	@ManyToOne
	private Product product;
	
	@ManyToOne
	private User user;
	
	private int quantity;
	
	private double totalprice;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private CustomerOrder customerOrder;

	public long getCartId() {
		return cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}

	public CustomerOrder getCustomerOrder() {
		return customerOrder;
	}

	public void setCustomerOrder(CustomerOrder customerOrder) {
		this.customerOrder = customerOrder;
	}

	@Override
	public String toString() {
		return "CartItem [cartId=" + cartId + ", product=" + product + ", user=" + user + ", quantity=" + quantity
				+ ", totalprice=" + totalprice + "]";
	}
	
}
