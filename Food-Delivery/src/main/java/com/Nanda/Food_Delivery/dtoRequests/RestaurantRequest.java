package com.Nanda.Food_Delivery.dtoRequests;


import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.Nanda.Food_Delivery.Model.Menu;
import com.Nanda.Food_Delivery.Model.Order_Entity;
import com.Nanda.Food_Delivery.enums.RestarauntCategory;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RestaurantRequest
{
	@JsonIgnore
	int id;
	
	String imageURL;
	
	String restaurantName;

	String location;

	RestarauntCategory restaurantCategory;

	String contactNumber;
	
	LocalTime opens;
	
	LocalTime closes;
	
	String adminUserName;
	
	String password;

	@JsonIgnore
	@ToString.Exclude
	Menu menu;

	@JsonIgnore
	@ToString.Exclude
	List<Order_Entity> order_entity = new ArrayList<>();

}
