package com.Nanda.Food_Delivery.dtoRequests;

import com.Nanda.Food_Delivery.Model.Cart;
import com.Nanda.Food_Delivery.Model.Restaurant;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OrderRequest
{
	@JsonIgnore
	int id;

	@JsonIgnore
	@ToString.Exclude
	FoodItemRequest foodItemRequest;

	int quantity;

	@JsonIgnore
	double totalCost;

	@JsonIgnore
	@ToString.Exclude
	Restaurant restaurant;

	@JsonIgnore
	@ToString.Exclude
	Cart cart;

    String customerEmail;
}
