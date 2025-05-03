package com.Nanda.Food_Delivery.hateoas;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.Nanda.Food_Delivery.Controller.MenuController;
import com.Nanda.Food_Delivery.Controller.RestaurantController;
import com.Nanda.Food_Delivery.dtoResponse.RestaurantResponse;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class RestaurantModelAssembler extends RepresentationModelAssemblerSupport<RestaurantResponse, EntityModel<RestaurantResponse>>{

	@SuppressWarnings("unchecked")
	public RestaurantModelAssembler(Class<?> controllerClass, Class<EntityModel<RestaurantResponse>> resourceType) {
		super(RestaurantController.class, (Class<EntityModel<RestaurantResponse>>) (Class<?>) EntityModel.class);
	}

	@Override
	public EntityModel<RestaurantResponse> toModel(RestaurantResponse entity) {
		return null;
	}
	
	public EntityModel<RestaurantResponse> toModel(RestaurantResponse entity, String key)
	{
		EntityModel<RestaurantResponse> entityModel;
		switch (key) 
		{
		
		case "create" : 
							{
								entityModel = EntityModel.of(entity, linkTo(methodOn(RestaurantController.class).findRestaurantById(entity.getId())).withSelfRel(),
																	 linkTo(methodOn(RestaurantController.class).UpdateRestaurant(entity.getId(), null)).withRel("update"),
																	 linkTo(methodOn(RestaurantController.class).findAllRestaurants()).withRel("all_restaurants"),
																	 linkTo(methodOn(MenuController.class).getMenu(entity.getId())).withRel("menu"));
								return entityModel;
							}
			case "GetOne" : 
							{
								entityModel = EntityModel.of(entity, linkTo(methodOn(RestaurantController.class).findRestaurantById(entity.getId())).withSelfRel(),
																	 linkTo(methodOn(RestaurantController.class).UpdateRestaurant(entity.getId(), null)).withRel("update"),
																	 linkTo(methodOn(RestaurantController.class).deleteRestaurant(entity.getId())).withRel("delete"),
																	 linkTo(methodOn(RestaurantController.class).findAllRestaurants()).withRel("all_restaurants"),
																	 linkTo(methodOn(MenuController.class).getMenu(entity.getId())).withRel("menu"));
								return entityModel;
							}
			case "GetOneForAdmin" : 
			{
				entityModel = EntityModel.of(entity, linkTo(methodOn(RestaurantController.class).findRestaurantById(entity.getId())).withSelfRel(),
													 linkTo(methodOn(RestaurantController.class).UpdateRestaurant(entity.getId(), null)).withRel("update"),
													 linkTo(methodOn(RestaurantController.class).deleteRestaurant(entity.getId())).withRel("delete"),
													 linkTo(methodOn(MenuController.class).getMenu(entity.getId())).withRel("menu"));
				return entityModel;
			}
			case "update" : 
							{
								entityModel = EntityModel.of(entity, linkTo(methodOn(RestaurantController.class).findRestaurantById(entity.getId())).withSelfRel(),
																	 linkTo(methodOn(RestaurantController.class).deleteRestaurant(entity.getId())).withRel("delete"),
																	 linkTo(methodOn(RestaurantController.class).findAllRestaurants()).withRel("all_restaurants"),
																	 linkTo(methodOn(MenuController.class).getMenu(entity.getId())).withRel("menu"));
								return entityModel;
							}
			case "delete" : 
							{
								entityModel = EntityModel.of(entity, linkTo(methodOn(RestaurantController.class).findRestaurantById(entity.getId())).withSelfRel(),
																	 linkTo(methodOn(RestaurantController.class).addRestaurant(null)).withRel("create"),
																	 linkTo(methodOn(RestaurantController.class).findAllRestaurants()).withRel("all_restaurants"));
								return entityModel;
							}
		    default:
		    					throw new IllegalArgumentException("Unexpected value: " + key);
		}
	}
}
