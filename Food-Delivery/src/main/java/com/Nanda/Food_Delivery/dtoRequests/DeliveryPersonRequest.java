package com.Nanda.Food_Delivery.dtoRequests;

import java.util.ArrayList;
import java.util.List;

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
public class DeliveryPersonRequest
{
    @JsonIgnore
	int id;

	String name;
	
	String phoneNo;

	Gender gender;

	int age;

	String email;

	String password;
	
	@JsonIgnore
	@ToString.Exclude
	@Builder.Default
	List<DeliveryRequest> order_entity = new ArrayList<>();
}
