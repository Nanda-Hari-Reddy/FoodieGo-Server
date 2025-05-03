package com.Nanda.Food_Delivery.Transformer;

import com.Nanda.Food_Delivery.Model.Order_Entity;
import com.Nanda.Food_Delivery.dtoRequests.OrderRequest;
import com.Nanda.Food_Delivery.dtoResponse.OrderResponse;

public class OrderTransformer {

	public static Order_Entity requestToEntity(OrderRequest request)
	{
		return Order_Entity.builder()
							  .quantity(request.getQuantity())
							  .build();
	}

	public static OrderResponse entityToResponse(Order_Entity entity)
	{
		if(entity==null) return null;
		return OrderResponse.builder()
			   .orderId(entity.getId())
			   .foodItem(FoodItemTransformer.entityToResponse(entity.getFoodItem()))
			   .orderQuantity(entity.getQuantity())
			   .orderTotal(entity.getTotalCost())
			   .restaurantResponse(RestaurantTransformer.entityToResponse(entity.getRestaurant()))
			   .cartId(entity.getCart().getId())
			   .customerId(entity.getCustomer().getId())
			   .customerEmail(entity.getCustomer().getEmail())
			   .build();
	}

}
