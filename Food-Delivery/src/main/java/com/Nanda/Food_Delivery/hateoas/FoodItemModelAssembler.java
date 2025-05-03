package com.Nanda.Food_Delivery.hateoas;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.Nanda.Food_Delivery.Controller.FoodItemController;
import com.Nanda.Food_Delivery.Controller.MenuController;
import com.Nanda.Food_Delivery.dtoResponse.FoodResponse;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@Component
public class FoodItemModelAssembler extends RepresentationModelAssemblerSupport<FoodResponse, EntityModel<FoodResponse>>
{
	
	@SuppressWarnings("unchecked")
	public FoodItemModelAssembler(Class<?> controllerClass, Class<EntityModel<FoodResponse>> resourceType) 
	{
		super(FoodItemController.class, (Class<EntityModel<FoodResponse>>) (Class<?>) EntityModel.class);
	}
	
	@Override
	public EntityModel<FoodResponse> toModel(FoodResponse entity) 
	{		
		return null;
	
	}
	
	public EntityModel<FoodResponse> toModel(FoodResponse entity, String apiContext) 
	{
		EntityModel<FoodResponse> entityModel;
		switch (apiContext) {
		case "GetOne": 
						{
							System.out.println(entity.getMenu());
							entityModel = EntityModel.of(entity,
									linkTo(methodOn(FoodItemController.class).getFoodItem(entity.getMenu().getId(), entity.getId())).withSelfRel(),
									linkTo(methodOn(FoodItemController.class).UpdateFoodItem(entity.getMenu().getId(), entity.getId(), null)).withRel("update"),
									linkTo(methodOn(FoodItemController.class).deleteFoodItem(entity.getMenu().getId(), entity.getId())).withRel("delete"),
									linkTo(methodOn(MenuController.class).getMenu(entity.getMenu().getId())).withRel("menu"));
							return entityModel;
						}
		case "update":
						{
							entityModel = EntityModel.of(entity,
									linkTo(methodOn(FoodItemController.class).getFoodItem(entity.getMenu().getId(), entity.getId())).withSelfRel(),
									linkTo(methodOn(FoodItemController.class).deleteFoodItem(entity.getMenu().getId(), entity.getId())).withRel("delete"),
									linkTo(methodOn(MenuController.class).getMenu(entity.getMenu().getId())).withRel("menu"));
							return entityModel;
						}
		case "delete":
						{
							entityModel = EntityModel.of(entity,
									linkTo(methodOn(MenuController.class).addMenu(null, entity.getMenu().getId())).withRel("create"),
									linkTo(methodOn(FoodItemController.class).getFoodItem(entity.getMenu().getId(), entity.getId())).withSelfRel(),
									linkTo(methodOn(MenuController.class).getMenu(entity.getMenu().getId())).withRel("menu"));
							return entityModel;
						}
		default:
						throw new IllegalArgumentException("Unexpected key: " + apiContext);
		}
	}
}
