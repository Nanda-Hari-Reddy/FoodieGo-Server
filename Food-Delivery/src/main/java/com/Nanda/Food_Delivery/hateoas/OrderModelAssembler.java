package com.Nanda.Food_Delivery.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.Nanda.Food_Delivery.Controller.CartController;
import com.Nanda.Food_Delivery.Controller.OrderController;
import com.Nanda.Food_Delivery.dtoResponse.OrderResponse;

@Component
public class OrderModelAssembler extends RepresentationModelAssemblerSupport<OrderResponse, EntityModel<OrderResponse>>{
	@SuppressWarnings("unchecked")
	public OrderModelAssembler(Class<?> controllerClass, Class<EntityModel<OrderResponse>> resourceType) {
		super(OrderController.class, (Class<EntityModel<OrderResponse>>) (Class<?>) EntityModel.class);
	}

	@Override
	public EntityModel<OrderResponse> toModel(OrderResponse entity)
	{
		EntityModel<OrderResponse> entityModel;
		entityModel = EntityModel.of(entity, linkTo(methodOn(CartController.class).retrieveOrderInCart(entity.getCustomerId(), entity.getOrderId())).withSelfRel(),
				 linkTo(methodOn(CartController.class).deleteOrder(entity.getCustomerId(), entity.getOrderId())).withRel("delete"),
				 linkTo(methodOn(CartController.class).retrieveCart(entity.getCustomerId())).withRel("all_orders"));
		return entityModel;
	}
	
}