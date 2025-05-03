package com.Nanda.Food_Delivery.Transformer;

import java.util.ArrayList;
import java.util.List;

import com.Nanda.Food_Delivery.Model.FoodItem;
import com.Nanda.Food_Delivery.Model.Menu;
import com.Nanda.Food_Delivery.Model.Restaurant;
import com.Nanda.Food_Delivery.dtoResponse.FoodResponse;
import com.Nanda.Food_Delivery.dtoResponse.MenuResponse;

public class MenuTransformer
{
	public static Menu requestToEntity(Menu request, Restaurant restaurant)
	{
		Menu menu = Menu.builder()
				.id(restaurant.getId())
				.restaurant(restaurant)
					.foodItemsList(request.getFoodItemsList())
				.build();
		return menu;

	}


	public static MenuResponse entityToResponse(Menu entity)
	{
		if(entity==null) {
			return null;
		}
		List<FoodItem> menuList = entity.getFoodItemsList();
		List<FoodResponse> menuResponseList = new ArrayList<>();
		for(FoodItem foodItem : menuList)
		{
			menuResponseList.add(FoodItemTransformer.entityToResponse(foodItem));
		}
		MenuResponse menuResponse = MenuResponse.builder()
									.id(entity.getId())
									.foodItemsList(menuResponseList)
									.build();
		return menuResponse;
	}
}
