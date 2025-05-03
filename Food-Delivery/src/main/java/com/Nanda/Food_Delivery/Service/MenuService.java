package com.Nanda.Food_Delivery.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Nanda.Food_Delivery.Model.FoodItem;
import com.Nanda.Food_Delivery.Model.Menu;
import com.Nanda.Food_Delivery.Model.Restaurant;
import com.Nanda.Food_Delivery.Repository.MenuRepository;
import com.Nanda.Food_Delivery.Repository.RestaurantRepository;
import com.Nanda.Food_Delivery.Transformer.MenuTransformer;
import com.Nanda.Food_Delivery.dtoRequests.MenuRequest;
import com.Nanda.Food_Delivery.dtoResponse.FoodResponse;
import com.Nanda.Food_Delivery.exception.MenuNotFoundException;
import com.Nanda.Food_Delivery.exception.RestaurantNotFoundException;

@Service
public class MenuService
{
	FoodItemService foodItemService;
	RestaurantRepository restaurantRepository;
	MenuRepository menuRepository;
	
	public MenuService(FoodItemService foodItemService, RestaurantRepository restaurantRepository,
			MenuRepository menuRepository) {
		super();
		this.foodItemService = foodItemService;
		this.restaurantRepository = restaurantRepository;
		this.menuRepository = menuRepository;
	}

	public Menu addFoodItemsToMenu(MenuRequest menuRequest, int rsntId)
	{
		FoodItem foodItem = foodItemService.addFoodItem(menuRequest);
		Menu menu;
		Restaurant restaurant;
		
		Optional<Restaurant> rsnt = restaurantRepository.findById(rsntId);
		if(rsnt.isEmpty()) throw new RestaurantNotFoundException("No restaurant Found with id = "+ rsntId);
		restaurant = rsnt.get();
		
		menu = getMenuByRestaurant(restaurant);
		if (menu != null) {
			List<FoodItem> menuList = menu.getFoodItemsList();
			menuList.add(foodItem);
			foodItem.setMenu(menu);
		}
		
		else
		{
			Menu newMenu = new Menu();
			newMenu.getFoodItemsList().add(foodItem);
			menu = MenuTransformer.requestToEntity(newMenu, restaurant);
			foodItem.setMenu(menu);
			
		}

		menuRepository.save(menu);
		return menu;
	}

	private Menu getMenuByRestaurant(Restaurant restaurant)
	{
		return menuRepository.findMenuByRestaurant(restaurant);
	}

	public List<FoodResponse> getMenuResponse(int restaurantId)
	{
		Optional<Menu> menu = menuRepository.findById(restaurantId);
		if(menu.isEmpty()) throw new MenuNotFoundException("There is no Menu for this Restaurant");
		
		return foodItemService.retrieveFoodItemsForMenu(restaurantId);
	}
}
