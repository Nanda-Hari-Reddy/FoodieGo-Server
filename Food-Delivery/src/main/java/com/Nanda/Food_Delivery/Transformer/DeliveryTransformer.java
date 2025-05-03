package com.Nanda.Food_Delivery.Transformer;

import com.Nanda.Food_Delivery.Model.Delivery;
import com.Nanda.Food_Delivery.dtoResponse.DeliveryResponse;

public class DeliveryTransformer 
{
	public static DeliveryResponse entityToResponse(Delivery entity)
	{			
		return DeliveryResponse.builder()
					.deliveryId(entity.getDelivery_id())
					.cost(entity.getCost())
					.restaurantName(entity.getRestaurantName())
					.customerName(entity.getCustomerName())
					.createdAt(entity.getCreatedAt())
					.deliveryTime(entity.getDeliveryTime())
				    .orderResponse(OrderTransformer.entityToResponse(entity.getOrder()))
				    .deliveryPersonResponse(DeliveryPersonTransformer.entityToResponse(entity.getDeliveryPerson()))
				    .build();
	}
}
