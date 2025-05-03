package com.Nanda.Food_Delivery.dtoResponse;

import java.util.ArrayList;
import java.util.List;

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

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {

//    String name;
//
//    String mobileNo;
//
//    String address;
//
//    CartResponse ca
	int id;

	String name;
	
	String imageURL;

	String email;

	@JsonIgnore
	String password;

	String mobileNo;

    Gender gender;

    @ToString.Exclude
	List<DeliveryAddressResponse> addressResponse;

    @JsonIgnore
    CartResponse cartResponse;

    @JsonIgnore
    @Builder.Default
    @ToString.Exclude
    List<Order_Entity> order_entity = new ArrayList<>();
}
