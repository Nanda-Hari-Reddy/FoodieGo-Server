package com.Nanda.Food_Delivery.dtoResponse;

import java.time.LocalDateTime;

import com.Nanda.Food_Delivery.Model.DeliveryAddressEmbedded;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeliveryResponse
{
	int deliveryId;

	OrderResponse orderResponse;

	double cost;

	String customerName;
	
	LocalDateTime createdAt;

	DeliveryAddressEmbedded addressEmbedded;

	String deliveryTime;
	
	String restaurantName;
	
	DeliveryPersonResponse deliveryPersonResponse;
}
