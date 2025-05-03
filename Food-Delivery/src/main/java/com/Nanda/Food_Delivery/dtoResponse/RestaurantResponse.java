package com.Nanda.Food_Delivery.dtoResponse;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.Nanda.Food_Delivery.Model.Order_Entity;
import com.Nanda.Food_Delivery.enums.Opened;
import com.Nanda.Food_Delivery.enums.RestarauntCategory;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class RestaurantResponse
{
    
	int id;

    String imageURL;
    
	String restaurantName;

	String location;

	RestarauntCategory restaurantCategory;

	String contactNumber;

	@Enumerated(EnumType.STRING)
	@Builder.Default
	Opened opened = Opened.OPENED;
	
	LocalTime opens;
	
	LocalTime closes;

	@ToString.Exclude
	MenuResponse menuResponse;
	
	String adminUserName;
	
	String adminPassword;

	@JsonIgnore
	@Builder.Default
	@ToString.Exclude
	List<Order_Entity> order_entity = new ArrayList<>();

}
