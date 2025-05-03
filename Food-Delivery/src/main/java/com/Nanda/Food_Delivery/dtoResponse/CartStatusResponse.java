package com.Nanda.Food_Delivery.dtoResponse;

import java.util.List;

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
public class CartStatusResponse {

	
    String customerName;

    String customerAddress;

    String customerMobile;

    double cartTotal;

    @ToString.Exclude
    List<FoodResponse> foodList;

    String restaurantName;

}
