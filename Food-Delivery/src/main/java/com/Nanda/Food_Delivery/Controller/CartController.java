package com.Nanda.Food_Delivery.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Nanda.Food_Delivery.Model.Order_Entity;
import com.Nanda.Food_Delivery.Service.CartService;
import com.Nanda.Food_Delivery.Service.OrderService;
import com.Nanda.Food_Delivery.Transformer.OrderTransformer;
import com.Nanda.Food_Delivery.dtoResponse.CartResponse;
import com.Nanda.Food_Delivery.dtoResponse.OrderResponse;



@RestController
@RequestMapping("/user/{userId}/cart")
public class CartController
{
	CartService cartService;
	OrderService orderService;

	public CartController(CartService cartService, OrderService orderService) 
	{
		this.cartService = cartService;
		this.orderService = orderService;
	}

	@GetMapping
	public ResponseEntity<CartResponse> retrieveCart(@PathVariable int userId)
	{
		CartResponse cartResponse = cartService.findCartforCustomer(userId);
		return new ResponseEntity<>(cartResponse, HttpStatus.ACCEPTED);
	}
	
	@GetMapping(path = "/{orderId}")
	public ResponseEntity<OrderResponse> retrieveOrderInCart(@PathVariable int userId, @PathVariable int orderId)
	{
		Order_Entity order_Entity = orderService.findOrder_Entity(orderId);
		return new ResponseEntity<>(OrderTransformer.entityToResponse(order_Entity), HttpStatus.ACCEPTED);
	}
	
	@PatchMapping(path = "/{orderId}")
	public ResponseEntity<OrderResponse> UpdateCart(@PathVariable int orderId, int  quantity) 
	{
		OrderResponse orderResponse = orderService.updateOrder(orderId, quantity);
		return new ResponseEntity<>(orderResponse, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(path = "/{orderId}")
	public ResponseEntity<OrderResponse> deleteOrder(@PathVariable int userId, @PathVariable int orderId)
	{
		OrderResponse orderResponse = orderService.deleteOrder(orderId);
		return new ResponseEntity<>(orderResponse, HttpStatus.ACCEPTED);
	}
	@RequestMapping(value = "/{orderId}", method = RequestMethod.HEAD)
	public ResponseEntity<Void> isOrderThere(@PathVariable int orderId)
	{
		if(orderService.findOrder(orderId)) return ResponseEntity.ok().build();
		else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	@GetMapping("/cartTotal")
	public ResponseEntity<Double> getCartTotal(@PathVariable int userId)
	{
		Double total = cartService.updateCartTotal(userId);
		return new ResponseEntity<>(total, HttpStatus.OK);
	}
}
