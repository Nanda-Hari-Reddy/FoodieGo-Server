package com.Nanda.Food_Delivery.dtoResponse;

import java.util.ArrayList;
import java.util.List;

import com.Nanda.Food_Delivery.Model.Menu;
import com.Nanda.Food_Delivery.Model.Order_Entity;
import com.Nanda.Food_Delivery.enums.FoodCategory;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodResponse
{

//    String dishName;
//
//    double price;
//
//    FoodCategory category;
//
//    boolean veg;
//
//    int quantityAdded;
	int id;

    String imageURL;
    
	String dishName;

	double price;

	FoodCategory foodCategory;

	@JsonIgnore
	@ToString.Exclude
	Menu menu;

	@JsonIgnore
	@ToString.Exclude
	@Builder.Default
	List<Order_Entity> orders = new ArrayList<>();
}
