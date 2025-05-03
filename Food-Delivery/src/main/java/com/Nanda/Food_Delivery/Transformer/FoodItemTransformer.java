package com.Nanda.Food_Delivery.Transformer;

import com.Nanda.Food_Delivery.Model.FoodItem;
import com.Nanda.Food_Delivery.dtoRequests.MenuRequest;
import com.Nanda.Food_Delivery.dtoResponse.FoodResponse;

public class FoodItemTransformer
{
	public static FoodItem requestToEntity(MenuRequest request)
	{
		FoodItem foodItem = FoodItem.builder()
							.dishName(request.getDishName())
							.price(request.getPrice())
							.foodCategory(request.getCategory())
							.imageURL(request.getImageURL())
							.build();
		return foodItem;
	}

	public static FoodResponse entityToResponse(FoodItem entity)
	{
		return FoodResponse.builder()
			   .id(entity.getId())
			   .dishName(entity.getDishName())
			   .price(entity.getPrice())
			   .foodCategory(entity.getFoodCategory())
			   .menu(entity.getMenu())
			   .imageURL(entity.getImageURL())
			   .build();
	}
}
