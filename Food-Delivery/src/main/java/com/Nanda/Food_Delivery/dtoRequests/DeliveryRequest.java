package com.Nanda.Food_Delivery.dtoRequests;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryRequest
{
	@JsonIgnore
	int deliveryId;

	@ToString.Exclude
	OrderRequest orderRequest;

	@JsonIgnore
	double cost;

	@JsonIgnore
	@ToString.Exclude
	CustomerRequest customer;

	@ToString.Exclude
	DeliveryAddressRequest address;

	@JsonIgnore
	String deliveryTime;

	@JsonIgnore
	@ToString.Exclude
	DeliveryPersonRequest deliveryPersonRequest;
}
