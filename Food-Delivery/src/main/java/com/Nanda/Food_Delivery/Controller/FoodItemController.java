package com.Nanda.Food_Delivery.Controller;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Nanda.Food_Delivery.Service.FoodItemService;
import com.Nanda.Food_Delivery.dtoResponse.FoodResponse;
import com.Nanda.Food_Delivery.exception.ErrorDetails;
import com.Nanda.Food_Delivery.hateoas.FoodItemModelAssembler;

@RestController
@RequestMapping("/restaurants/{restaurantId}/menu")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class FoodItemController
{
	FoodItemService foodItemService;
	FoodItemModelAssembler modelAssembler;
	ErrorDetails errorDetails;
	
	
	public FoodItemController(FoodItemService foodItemService, FoodItemModelAssembler modelAssembler,
			ErrorDetails errorDetails) {
		super();
		this.foodItemService = foodItemService;
		this.modelAssembler = modelAssembler;
		this.errorDetails = errorDetails;
		errorDetails.setMessage("No requested body found");
		errorDetails.setTimestamp(LocalDateTime.now());
	}

	@PatchMapping(path = "/{foodItemId}")
	public ResponseEntity<EntityModel<FoodResponse>> UpdateFoodItem(@PathVariable int restaurantId, @PathVariable int foodItemId,  @RequestBody Map<String, Object> updates) 
	{
		if(updates==null) return new ResponseEntity(errorDetails, HttpStatus.NOT_ACCEPTABLE);
		System.out.println(updates+" updatessssssssssssssssssssssssssssssssssssssssssssssssssss");
		FoodResponse foodItemResponse = foodItemService.updateFoodItem(foodItemId, updates);
		EntityModel<FoodResponse> entityModel = modelAssembler.toModel(foodItemResponse, "update");
		return ResponseEntity
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
	}
	
	@DeleteMapping(path = "/{foodItemId}")
	public ResponseEntity<EntityModel<FoodResponse>> deleteFoodItem(@PathVariable int restaurantId, @PathVariable int foodItemId)
	{
		FoodResponse foodItemResponse = foodItemService.deleteFoodItem(foodItemId);
		EntityModel<FoodResponse> entityModel = modelAssembler.toModel(foodItemResponse, "delete");
		return new ResponseEntity<>(entityModel, HttpStatus.ACCEPTED);
	}
	
	@GetMapping(path = "/{foodItemId}")
	public ResponseEntity<EntityModel<FoodResponse>> getFoodItem(@PathVariable int restaurantId, @PathVariable int foodItemId)
	{
		FoodResponse foodItemResponse = foodItemService.findFoodItem(foodItemId);
		EntityModel<FoodResponse> entityModel = modelAssembler.toModel(foodItemResponse, "GetOne");
		return new ResponseEntity<>(entityModel, HttpStatus.ACCEPTED);
	}
	
	@PatchMapping("/{foodItemId}/profilePicture")
	public ResponseEntity<EntityModel<FoodResponse>> UpdateFoodItemPicture(@PathVariable int restaurantId, @PathVariable int foodItemId,  String imageURL) 
	{
		FoodResponse foodItemResponse = foodItemService.updateFoodItemPicture(foodItemId, imageURL);
		EntityModel<FoodResponse> entityModel = modelAssembler.toModel(foodItemResponse, "update");
		return ResponseEntity
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
	}
}
