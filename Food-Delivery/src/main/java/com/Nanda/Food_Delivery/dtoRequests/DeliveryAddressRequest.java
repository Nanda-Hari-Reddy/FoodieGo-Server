package com.Nanda.Food_Delivery.dtoRequests;


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
public class DeliveryAddressRequest
{
	@JsonIgnore
	int id;

	String street;

	String area;

	String city;

	@JsonIgnore
	@ToString.Exclude
	CustomerRequest customerRequest;
}
