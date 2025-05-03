package com.Nanda.Food_Delivery.dtoRequests;


import com.Nanda.Food_Delivery.enums.FoodCategory;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class MenuRequest
{
	String imageURL;
	
	String dishName;

	double price;

	FoodCategory category;
	
	@JsonIgnore
    boolean available;
}
