package com.Nanda.Food_Delivery.dtoResponse;


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
public class OrderResponse
{

    int orderId;

//    String customerName;
//
//    String customerMobile;
//
//    String deliveryPartnerName;
//
//    String deliveryPartnerMobile;

    @ToString.Exclude
    FoodResponse foodItem;

    int orderQuantity;

    double orderTotal;

    @ToString.Exclude
    RestaurantResponse restaurantResponse;

    @JsonIgnore
    int cartId;
    
    @JsonIgnore
    int customerId;
    
    @JsonIgnore
    String customerEmail;
}
