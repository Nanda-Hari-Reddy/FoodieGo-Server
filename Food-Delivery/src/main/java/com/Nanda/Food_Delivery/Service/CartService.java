package com.Nanda.Food_Delivery.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Nanda.Food_Delivery.Model.Cart;
import com.Nanda.Food_Delivery.Model.Customer;
import com.Nanda.Food_Delivery.Model.Order_Entity;
import com.Nanda.Food_Delivery.Repository.CartRepository;
import com.Nanda.Food_Delivery.Repository.CustomerRepository;
import com.Nanda.Food_Delivery.Repository.OrderRepository;
import com.Nanda.Food_Delivery.Transformer.CartTransformer;
import com.Nanda.Food_Delivery.dtoResponse.CartResponse;

@Service
public class CartService
{
	
	@Autowired
	CartRepository cartRepository;
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	CustomerRepository customerRepository;
	
	public Cart addOrderTocart(Order_Entity order_Entity, Customer customer)
	{
		Optional<Cart> iscartThere = cartRepository.findByCustomerId(customer.getId());
		if(!iscartThere.isEmpty())
		{
			Cart cart = iscartThere.get();
			cart.setCartTotal(cart.getCartTotal()+order_Entity.getTotalCost());
			cartRepository.save(cart);
			return cart;
		}
		Cart newCart = new Cart();
		newCart.getOrders().add(order_Entity);
		newCart.setCartTotal(newCart.getCartTotal()+order_Entity.getTotalCost());
		newCart.setCustomer(customer);
		cartRepository.save(newCart);
		return newCart;
		
	}

	public CartResponse findCartforCustomer(int userId)
	{
		
		Optional<Cart>  isCartThere = cartRepository.findByCustomerId(userId);

		if(isCartThere.isEmpty()) return null;
		
		Cart cart = isCartThere.get();
		CartResponse cartResponse = CartTransformer.entityToResponse(cart);
		return cartResponse;
		
	}
	
	public Cart updateCart(double deviation, Customer customer, String plus_minus)
	{
		Cart cart = cartRepository.findByCustomerId(customer.getId()).get();
		if(plus_minus.equals("plus"))  cart.setCartTotal(cart.getCartTotal()+(deviation));
		else  cart.setCartTotal(cart.getCartTotal()-deviation);
		cartRepository.save(cart);
		return cart;
	}

	public Double updateCartTotal(int customerId) 
	{
		Optional<Customer> customer = customerRepository.findById(customerId);
		List<Order_Entity> orders =  orderRepository.findByCustomer(customer.get());
		double total = orders.stream().mapToDouble(Order_Entity::getTotalCost).sum();
		Optional<Cart> cart = cartRepository.findByCustomerId(customerId);
		cart.get().setCartTotal(total);
		return total;
	}
}
