package com.Nanda.Food_Delivery.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Nanda.Food_Delivery.Model.Cart;
import com.Nanda.Food_Delivery.Model.Customer;
import com.Nanda.Food_Delivery.Model.FoodItem;
import com.Nanda.Food_Delivery.Model.Order_Entity;
import com.Nanda.Food_Delivery.Model.Restaurant;
import com.Nanda.Food_Delivery.Repository.CustomerRepository;
import com.Nanda.Food_Delivery.Repository.FoodItemsRepository;
import com.Nanda.Food_Delivery.Repository.OrderRepository;
import com.Nanda.Food_Delivery.Repository.RestaurantRepository;
import com.Nanda.Food_Delivery.Transformer.OrderTransformer;
import com.Nanda.Food_Delivery.dtoRequests.OrderRequest;
import com.Nanda.Food_Delivery.dtoResponse.OrderResponse;
import com.Nanda.Food_Delivery.exception.FoodItemNotFoundException;
import com.Nanda.Food_Delivery.exception.OrderNotFoundException;
import com.Nanda.Food_Delivery.exception.RestaurantNotFoundException;
import com.Nanda.Food_Delivery.exception.UserNotFoundException;

@Service
public class OrderService
{
	OrderRepository orderRepository;
	FoodItemsRepository foodItemsRepository;
	CustomerRepository customerRepository;
	RestaurantRepository restaurantRepository;
	CartService cartService;
	
	public OrderService(OrderRepository orderRepository, FoodItemsRepository foodItemsRepository,
			CustomerRepository customerRepository, RestaurantRepository restaurantRepository, CartService cartService) {
		super();
		this.orderRepository = orderRepository;
		this.foodItemsRepository = foodItemsRepository;
		this.customerRepository = customerRepository;
		this.restaurantRepository = restaurantRepository;
		this.cartService = cartService;
	}

	public OrderResponse addOrder(OrderRequest orderRequest, int foodItemId, int restaurantId)
	{
		Optional<FoodItem> foodItem = foodItemsRepository.findById(foodItemId);
		if(foodItem.isEmpty()) throw new FoodItemNotFoundException("No foodItm Found With id = "+foodItemId);
		Optional<Order_Entity> order =  orderRepository.findByFoodItem(foodItem.get());
		if(!order.isEmpty() && orderRequest.getCustomerEmail().equals(order.get().getCustomer().getEmail()))
		{
			Order_Entity order_Entity = order.get();
			order_Entity.setQuantity(order_Entity.getQuantity()+orderRequest.getQuantity());
			
			if(orderRequest.getQuantity()>0) order_Entity.setTotalCost(order_Entity.getTotalCost()+foodItem.get().getPrice());
			else order_Entity.setTotalCost(order_Entity.getTotalCost()-foodItem.get().getPrice());
			orderRepository.save(order_Entity);
			Optional<Customer> customer = customerRepository.findByEmail(orderRequest.getCustomerEmail());
			if(customer.isEmpty()) throw new UserNotFoundException("No user found with email "+orderRequest.getCustomerEmail());
			Customer ctmr = customer.get();
			if(orderRequest.getQuantity()>0) cartService.updateCart(foodItem.get().getPrice(), ctmr, "plus");
			else cartService.updateCart(foodItem.get().getPrice(), ctmr, "minus");
			return OrderTransformer.entityToResponse(order_Entity);
		}
		Order_Entity newOrder = Order_Entity.builder()
		  .quantity(1)
		  .totalCost(foodItem.get().getPrice())
		  .foodItem(foodItem.get())
		  .customer(customerRepository.findByEmail(orderRequest.getCustomerEmail()).get())
		  .build();
		
		Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
		if(restaurant.isEmpty()) throw new RestaurantNotFoundException("No Restaurant Found With id = "+restaurantId);
		
		Restaurant rsnt = restaurant.get();
		newOrder.setRestaurant(rsnt);
		rsnt.getOrder_entity().add(newOrder);

		Optional<Customer> customer = customerRepository.findByEmail(orderRequest.getCustomerEmail());
		
		if(customer.isEmpty()) throw new UserNotFoundException("No user found with email "+orderRequest.getCustomerEmail());
		Customer ctmr = customer.get();
		newOrder.setCustomer(ctmr);
		ctmr.getOrderEntities().add(newOrder);

		Cart cart = cartService.addOrderTocart(newOrder, ctmr);
		cartService.updateCartTotal(customer.get().getId());
		newOrder.setCart(cart);

		orderRepository.save(newOrder);
		return OrderTransformer.entityToResponse(newOrder);
	}
	
	public Order_Entity findOrder_Entity(int orderId)
	{
		Optional<Order_Entity> order = orderRepository.findById(orderId);
		if(order.isEmpty()) throw new OrderNotFoundException("there is no Order with id "+orderId);
		return order.get();
		
	}

	public OrderResponse updateOrder(int orderId, int quantity)
	{
		Order_Entity order_Entity = findOrder_Entity(orderId);
		int actualQuantity = order_Entity.getQuantity();
		double plusCost = order_Entity.getFoodItem().getPrice()*(quantity-actualQuantity);
		order_Entity.setQuantity(quantity);
		order_Entity.setTotalCost(order_Entity.getTotalCost()+plusCost);
		cartService.updateCart(plusCost, order_Entity.getCustomer(), "plus");
		orderRepository.save(order_Entity);
		OrderResponse orderResponse = OrderTransformer.entityToResponse(order_Entity);
		return orderResponse;
	}

	public OrderResponse deleteOrder(int orderId) 
	{
		Order_Entity order_Entity = findOrder_Entity(orderId);
		double minusCost = order_Entity.getTotalCost();
		cartService.updateCart(minusCost, order_Entity.getCustomer(), "minus");
		orderRepository.delete(order_Entity);
		OrderResponse orderResponse = OrderTransformer.entityToResponse(order_Entity);
		return orderResponse;
	}

	public boolean findOrder(int orderId) 
	{
		Optional<Order_Entity> order = orderRepository.findById(orderId);
		if(order.isEmpty()) return false;
		return true;
	}
}
