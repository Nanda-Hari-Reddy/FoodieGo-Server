package com.Nanda.Food_Delivery.Transformer;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.Nanda.Food_Delivery.Model.Cart;
import com.Nanda.Food_Delivery.Model.Order_Entity;
import com.Nanda.Food_Delivery.dtoResponse.CartResponse;
import com.Nanda.Food_Delivery.dtoResponse.OrderResponse;

public class CartTransformer
{
	public static CartResponse entityToResponse(Cart entity)
	{
		if(entity==null) return null;
		List<Order_Entity> orders = entity.getOrders();
		Function<Order_Entity, OrderResponse> OrdersToorderResponses =
								order -> OrderTransformer.entityToResponse(order);
		List<OrderResponse> orderResponses =
					orders.stream().map(OrdersToorderResponses).collect(Collectors.toList());

		return CartResponse.builder()
			   .cartId(entity.getId())
			   .cartTotal(entity.getCartTotal())
			   .orderResponseList(orderResponses)
			   .customerId(entity.getCustomer().getId())
			   .build();
	}
}
