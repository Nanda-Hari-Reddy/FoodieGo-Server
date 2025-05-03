package com.Nanda.Food_Delivery.dtoRequests;


import java.util.ArrayList;
import java.util.List;

import com.Nanda.Food_Delivery.Model.Cart;
import com.Nanda.Food_Delivery.Model.Order_Entity;
import com.Nanda.Food_Delivery.enums.Gender;
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
public class CustomerRequest
{
    @JsonIgnore
	int id;

    String imageURL;
    
	String name;

	String email;

	String password;

	String mobileNo;

    Gender gender;

    @ToString.Exclude
	DeliveryAddressRequest addressRequest;

	@JsonIgnore
    Cart cart;

	@JsonIgnore
	@ToString.Exclude
	@Builder.Default
    List<Order_Entity> order_entity = new ArrayList<>();
}