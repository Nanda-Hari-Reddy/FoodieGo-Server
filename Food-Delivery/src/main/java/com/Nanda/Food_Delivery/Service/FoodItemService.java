package com.Nanda.Food_Delivery.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.Nanda.Food_Delivery.Model.FoodItem;
import com.Nanda.Food_Delivery.Repository.FoodItemsRepository;
import com.Nanda.Food_Delivery.Transformer.FoodItemTransformer;
import com.Nanda.Food_Delivery.dtoRequests.MenuRequest;
import com.Nanda.Food_Delivery.dtoResponse.FoodResponse;
import com.Nanda.Food_Delivery.exception.FoodItemNotFoundException;

@Service
public class FoodItemService
{
	@Autowired
	FoodItemsRepository foodItemsRepository;

	public FoodItem addFoodItem(MenuRequest menuRequest)
	{
		FoodItem foodItem = FoodItemTransformer
							.requestToEntity(menuRequest);
		return foodItem;

	}

	public void saveFoodItemToRepo(FoodItem foodItem)
	{
		foodItemsRepository.save(foodItem);
	}

	public List<FoodResponse> retrieveFoodItemsForMenu(int menuId)
	{

		List<FoodItem> items =  foodItemsRepository.findByMenuId(menuId);
		if(items==null) throw new FoodItemNotFoundException("Items are empty in the menu");
		List<FoodResponse> foodItemResponseList = new ArrayList<>();

		for(FoodItem foodItem : items)
		{
			foodItemResponseList.add(FoodItemTransformer.entityToResponse(foodItem));
		}
		return foodItemResponseList;
	}

	public List<FoodResponse> itemsWithMaxorders()
	{
		List<FoodItem> items  = foodItemsRepository.findAll();
		if(items==null) throw new FoodItemNotFoundException("Items are empty");
		Function<FoodItem, FoodResponse> itemsToResponses = foodItem -> FoodItemTransformer.entityToResponse(foodItem);
		List<FoodResponse>  itemResponses = items.subList(0, items.size()/2)
								  .stream()
								  .map(itemsToResponses)
								  .collect(Collectors.toList());
		return itemResponses;
	}

	public FoodResponse updateFoodItem(int foodItemId, Map<String, Object> updates) 
	{
		Optional<FoodItem> isFoodItemThere = foodItemsRepository.findById(foodItemId);
		if(isFoodItemThere.isEmpty()) throw new FoodItemNotFoundException("No foodItm Found With id = "+foodItemId);
		FoodItem foodItem = isFoodItemThere.get();
		updates.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(FoodItem.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, foodItem, value);
            }
        });
		FoodItem foodItemSaved = foodItemsRepository.save(foodItem);
		FoodResponse foodItemResponse = FoodItemTransformer.entityToResponse(foodItemSaved);
		return foodItemResponse;
	}

	public FoodResponse deleteFoodItem(int foodItemId) 
	{
		Optional<FoodItem> isFoodItemThere = foodItemsRepository.findById(foodItemId);
		if(isFoodItemThere.isEmpty()) throw new FoodItemNotFoundException("No foodItm Found With id = "+foodItemId);
		foodItemsRepository.delete(isFoodItemThere.get());
		FoodResponse foodItemResponse = FoodItemTransformer.entityToResponse(isFoodItemThere.get());
		return foodItemResponse;
	}
	
	public FoodResponse findFoodItem(int foodItemId)
	{
		Optional<FoodItem> food =  foodItemsRepository.findById(foodItemId);
		if(food.isEmpty()) throw new FoodItemNotFoundException("No foodItm Found With id = "+foodItemId);
		return FoodItemTransformer.entityToResponse(food.get());
	}

	public FoodResponse updateFoodItemPicture(int foodItemId, String imageURL) 
	{
		Optional<FoodItem> foodItem = foodItemsRepository.findById(foodItemId);
		if(foodItem.isEmpty()) throw new FoodItemNotFoundException("No foodItm Found With id = "+foodItemId);
		foodItem.get().setImageURL(imageURL);
		foodItemsRepository.save(foodItem.get());
		FoodResponse foodItemResponse = FoodItemTransformer.entityToResponse(foodItem.get());
		return foodItemResponse;
	}
}
