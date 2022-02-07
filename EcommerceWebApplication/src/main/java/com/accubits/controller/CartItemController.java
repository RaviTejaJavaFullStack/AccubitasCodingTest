package com.accubits.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.accubits.model.CartItem;
import com.accubits.model.CustomerOrder;
import com.accubits.model.Product;
import com.accubits.model.User;
import com.accubits.service.CartItemService;
import com.accubits.service.ProductService;
import com.accubits.service.UserService;

@Controller
@RequestMapping(value="/api/cart")
public class CartItemController {
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;

	@PreAuthorize("hasRole('USER')")
	@PostMapping(value = "/addToCart/{productId}")
	public ResponseEntity<?> addToCart(@PathVariable long productId, @RequestParam int requestedQuantity,@AuthenticationPrincipal Principal principal,HttpServletRequest request) {
		try {
			String username = principal.getName();
			Product product = productService.findById(productId);
			User user = userService.findOne(username);
			List<CartItem> cartItems = cartItemService.getCart(username);
			if(product !=  null) {
				if(!cartItems.isEmpty()) {
					for (CartItem cartItem : cartItems) {
						if (cartItem.getProduct().getId() == productId) {
							int qty = cartItem.getQuantity();
							qty = qty + requestedQuantity;
							cartItem.setQuantity(qty);
							cartItem.setTotalprice(qty * product.getPrice());
							cartItemService.addToCart(cartItem);
						}else {
							cartItem = new CartItem();
							cartItem.setProduct(product);
							cartItem.setQuantity(requestedQuantity);
							cartItem.setUser(user);
							cartItem.setTotalprice(requestedQuantity * product.getPrice());
							cartItemService.addToCart(cartItem);	
						}
					}	
				}else {
					CartItem cartItem = new CartItem();
					cartItem.setProduct(product);
					cartItem.setQuantity(requestedQuantity);
					cartItem.setUser(user);
					cartItem.setTotalprice(requestedQuantity * product.getPrice());
					cartItemService.addToCart(cartItem);	
				}
				cartItems = cartItemService.getCart(username);
				double grandTotal = 0.0;
				for (CartItem cartItem : cartItems) {
					grandTotal = grandTotal + cartItem.getTotalprice();
				}
				CustomerOrder customerOrder = new CustomerOrder();
				customerOrder.setPurchaseDate(new Date());
				customerOrder.setGrandTotal(grandTotal);
				customerOrder.setUser(user);
				customerOrder.setCartItems(cartItems);
				if (!cartItems.isEmpty()) {
					customerOrder = cartItemService.createCustomerOrder(customerOrder);
				}
				for (CartItem cItem : cartItems) {
					Product p = cItem.getProduct();
					p.setQuantity(p.getQuantity() - cItem.getQuantity());
					productService.updateProducts(p);
					cartItemService.removeCartItem(cItem.getCartId());
				}	
				return new ResponseEntity<>(customerOrder,HttpStatus.OK);
			}else {
				return new ResponseEntity<>("Given Product Not Found",HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "/getCart")
	public ResponseEntity<?> getCart(@AuthenticationPrincipal Principal principal, HttpSession session) {
		try {
			String username = principal.getName();
			List<CartItem> cartItems = cartItemService.getCart(username);
			return new ResponseEntity<>(cartItems,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "/removeCartItem/{cartId}")
	public ResponseEntity<?> removeCartItem(@PathVariable long cartId) {
		try {
			cartItemService.removeCartItem(cartId);
			return new ResponseEntity<>("CartItem Removed Successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("No Item present in the cart", HttpStatus.NO_CONTENT);
		}
	}

	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "/clearCart")
	public ResponseEntity<?> clearCart(@AuthenticationPrincipal Principal principal) {
		try {
			List<CartItem> cartItems = cartItemService.getCart(principal.getName());
			for (CartItem cartItem : cartItems) {
				cartItemService.removeCartItem(cartItem.getCartId());
			}
			return new ResponseEntity<>("No Item present in the cart", HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
