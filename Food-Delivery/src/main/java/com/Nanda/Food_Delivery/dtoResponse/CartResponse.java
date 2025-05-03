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
public class CartResponse
{
	int cartId;

    double cartTotal;

    @ToString.Exclude
    List<OrderResponse> orderResponseList;

    @ToString.Exclude
    int customerId;
}
