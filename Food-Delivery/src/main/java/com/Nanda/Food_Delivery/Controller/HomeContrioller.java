package com.Nanda.Food_Delivery.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Nanda.Food_Delivery.Service.FoodItemService;
import com.Nanda.Food_Delivery.Service.RestaurantService;
import com.Nanda.Food_Delivery.dtoResponse.FoodResponse;
import com.Nanda.Food_Delivery.dtoResponse.RestaurantResponse;

@RestController
@RequestMapping("/home")
public class HomeContrioller
{
	@Autowired
	RestaurantService restaurantService;
	@Autowired
	FoodItemService foodItemService;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping
	public ResponseEntity<Map<FoodResponse, Object>> retrievedataForHomePage()
	{
		List<RestaurantResponse> restaurants =  restaurantService.retrieveallRestaurants();
		List<FoodResponse> foods =  foodItemService.itemsWithMaxorders();
		Map<String, Object> restaurant_food = new HashMap<>();
		restaurant_food.put("Restaurants", restaurants);
		restaurant_food.put("Foods", foods);

		return new ResponseEntity(restaurant_food, HttpStatus.ACCEPTED);
	}
}
