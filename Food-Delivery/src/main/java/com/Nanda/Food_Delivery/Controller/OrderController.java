package com.Nanda.Food_Delivery.Controller;

import java.time.LocalDateTime;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Nanda.Food_Delivery.Service.OrderService;
import com.Nanda.Food_Delivery.dtoRequests.OrderRequest;
import com.Nanda.Food_Delivery.dtoResponse.OrderResponse;
import com.Nanda.Food_Delivery.exception.ErrorDetails;
import com.Nanda.Food_Delivery.hateoas.OrderModelAssembler;

@RestController
@SuppressWarnings({ "unchecked", "rawtypes" })
public class OrderController
{
	OrderService orderService;
	ErrorDetails errorDetails;
	OrderModelAssembler modelAssembler;
	public OrderController(OrderService orderService, ErrorDetails errorDetails, OrderModelAssembler modelAssembler) {
		this.orderService = orderService;
		this.modelAssembler = modelAssembler;
		errorDetails.setMessage("No requested body found");
		errorDetails.setTimestamp(LocalDateTime.now());
	}

	@PostMapping("/restaurants/{restaurantId}/menu/{foodItemId}")
	public ResponseEntity<EntityModel<OrderResponse>> addOrder
	(@RequestBody OrderRequest orderRequest, @PathVariable int foodItemId, @PathVariable int restaurantId)
	{
		if(orderRequest==null) return new ResponseEntity(errorDetails, HttpStatus.NOT_ACCEPTABLE);
		OrderResponse orderResponse = orderService.addOrder(orderRequest, foodItemId, restaurantId);
//		URI location = ServletUriComponentsBuilder
//				   .fromCurrentRequest()
//				   .replacePath("/user/{userId}/cart/{orderId}")
//				   .buildAndExpand(orderResponse.getCustomerId(), orderResponse.getOrderId())
//				   .toUri();
//		return ResponseEntity.created(location).build();
		EntityModel<OrderResponse> entityModel = modelAssembler.toModel(orderResponse);
		return new ResponseEntity<>(entityModel, HttpStatus.ACCEPTED);
	}
}
