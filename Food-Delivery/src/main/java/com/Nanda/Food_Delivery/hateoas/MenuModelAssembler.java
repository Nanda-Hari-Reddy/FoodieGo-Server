package com.Nanda.Food_Delivery.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.Nanda.Food_Delivery.Controller.MenuController;
import com.Nanda.Food_Delivery.Controller.RestaurantController;
import com.Nanda.Food_Delivery.dtoResponse.MenuResponse;

@Component
public class MenuModelAssembler extends RepresentationModelAssemblerSupport<MenuResponse, EntityModel<MenuResponse>>{

	@SuppressWarnings("unchecked")
	public MenuModelAssembler(Class<?> controllerClass, Class<EntityModel<MenuResponse>> resourceType) {
		super(MenuController.class, (Class<EntityModel<MenuResponse>>) (Class<?>) EntityModel.class);
	}

	@Override
	public EntityModel<MenuResponse> toModel(MenuResponse entity) 
	{
		EntityModel<MenuResponse> entityModel = EntityModel.of(entity, linkTo(methodOn(MenuController.class).getMenu(entity.getId())).withSelfRel(),
				 													   linkTo(methodOn(RestaurantController.class).findRestaurantById(entity.getId())).withRel("restaurant"));
		return entityModel;
	}
	
}
