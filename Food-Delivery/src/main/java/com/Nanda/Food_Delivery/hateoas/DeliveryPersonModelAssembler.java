package com.Nanda.Food_Delivery.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.Nanda.Food_Delivery.Controller.DeliveryPersonController;
import com.Nanda.Food_Delivery.dtoResponse.DeliveryPersonResponse;

@Component
public class DeliveryPersonModelAssembler extends RepresentationModelAssemblerSupport<DeliveryPersonResponse, EntityModel<DeliveryPersonResponse>>
{

	@SuppressWarnings("unchecked")
	public DeliveryPersonModelAssembler(Class<?> controllerClass,
			Class<EntityModel<DeliveryPersonResponse>> resourceType) 
	{
		super(DeliveryPersonResponse.class, (Class<EntityModel<DeliveryPersonResponse>>) (Class<?>) EntityModel.class);
	}

	@Override
	public EntityModel<DeliveryPersonResponse> toModel(DeliveryPersonResponse entity) 
	{
		return null;
	}
	
	public EntityModel<DeliveryPersonResponse> toModel(DeliveryPersonResponse entity, String apiContext)
	{
		EntityModel<DeliveryPersonResponse> entityModel;
		switch (apiContext) 
		{
		case "GetOne": 
						{
							entityModel = EntityModel.of(entity,
									linkTo(methodOn(DeliveryPersonController.class).retrieveDeliveryPerson(entity.getId())).withSelfRel(),
									linkTo(methodOn(DeliveryPersonController.class).UpdateDeliveryPerson(entity.getId(), null)).withRel("update"),
									linkTo(methodOn(DeliveryPersonController.class).deleteDeliveryPerson(entity.getId())).withRel("delete"),
									linkTo(methodOn(DeliveryPersonController.class).retrieveAllDeliveryPerson()).withRel("all_DeliveryPersons"),
									linkTo(methodOn(DeliveryPersonController.class).deliveriesAssained(entity.getId())).withRel("deliveries"));
							return entityModel;
						}
		case "update":
						{
							entityModel = EntityModel.of(entity,
									linkTo(methodOn(DeliveryPersonController.class).retrieveDeliveryPerson(entity.getId())).withSelfRel(),
									linkTo(methodOn(DeliveryPersonController.class).deleteDeliveryPerson(entity.getId())).withRel("delete"),
									linkTo(methodOn(DeliveryPersonController.class).retrieveAllDeliveryPerson()).withRel("all_DeliveryPersons"));
							return entityModel;
						}
		case "delete":
						{
							entityModel = EntityModel.of(entity,
									linkTo(methodOn(DeliveryPersonController.class).retrieveDeliveryPerson(entity.getId())).withSelfRel(),
									linkTo(methodOn(DeliveryPersonController.class).addDeliveyPerson(null)).withRel("create"),
									linkTo(methodOn(DeliveryPersonController.class).retrieveAllDeliveryPerson()).withRel("all_DeliveryPersons"));
							return entityModel;
						}
		default:
						throw new IllegalArgumentException("Unexpected key: " + apiContext);
		}
	}
}
