package com.Nanda.Food_Delivery.Controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.Nanda.Food_Delivery.Model.Menu;
import com.Nanda.Food_Delivery.Service.MenuService;
import com.Nanda.Food_Delivery.dtoRequests.MenuRequest;
import com.Nanda.Food_Delivery.dtoResponse.FoodResponse;
import com.Nanda.Food_Delivery.exception.ErrorDetails;
import com.Nanda.Food_Delivery.hateoas.FoodItemModelAssembler;

@RestController
@RequestMapping("/restaurants/{restaurantId}/menu")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class MenuController
{
	@Autowired
	MenuService menuService;
	@Autowired
	FoodItemModelAssembler modelAssembler;
	ErrorDetails errorDetails;
	
	public MenuController(MenuService menuService, FoodItemModelAssembler modelAssembler, ErrorDetails errorDetails) {
		super();
		this.menuService = menuService;
		this.modelAssembler = modelAssembler;
		this.errorDetails = errorDetails;
		errorDetails.setMessage("No requested body found");
		errorDetails.setTimestamp(LocalDateTime.now());
	}

	@PostMapping
	public ResponseEntity<Menu> addMenu(@RequestBody MenuRequest menuRequest, @PathVariable int restaurantId)
	{
		System.out.println(menuRequest+"%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");

		if(menuRequest==null) return new ResponseEntity(errorDetails, HttpStatus.NOT_ACCEPTABLE);

		Menu menu = menuService.addFoodItemsToMenu(menuRequest, restaurantId);
		URI location = ServletUriComponentsBuilder
				   .fromCurrentRequest().path("/{menuid}")
				   .buildAndExpand(menu.getId())
				   .toUri();
		return ResponseEntity.created(location).build();
	}

	@GetMapping
	public ResponseEntity<CollectionModel<EntityModel<FoodResponse>>> getMenu(@PathVariable int restaurantId)
	{

		List<FoodResponse> items =  menuService.getMenuResponse(restaurantId);
		List<EntityModel<FoodResponse>> entityModels = items.stream()
													.map(foodResponse -> modelAssembler.toModel(foodResponse, "GetOne"))
													.collect(Collectors.toList());
		CollectionModel<EntityModel<FoodResponse>> entityModelCollection = CollectionModel.of(entityModels, linkTo(methodOn(CustomerController.class).retrieveAllCustomers()).withSelfRel());
		return new ResponseEntity<>(entityModelCollection, HttpStatus.ACCEPTED);
		
	}
	
}