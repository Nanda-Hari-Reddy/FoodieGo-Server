package com.Nanda.Food_Delivery.hateoas;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.Nanda.Food_Delivery.Controller.DeliveryAddressController;
import com.Nanda.Food_Delivery.Model.Customer;
import com.Nanda.Food_Delivery.dtoResponse.DeliveryAddressResponse;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@Component
public class DeliveryAddressModelAssembler extends RepresentationModelAssemblerSupport<DeliveryAddressResponse, EntityModel<DeliveryAddressResponse>>
{

	@SuppressWarnings("unchecked")
	public DeliveryAddressModelAssembler(Class<?> controllerClass, Class<EntityModel<Customer>> resourceType) 
	{
		super(DeliveryAddressController.class, (Class<EntityModel<DeliveryAddressResponse>>) (Class<?>) EntityModel.class);
	}
	
	@Override
	public EntityModel<DeliveryAddressResponse> toModel(DeliveryAddressResponse entity) 
	{		
		return null;
	
	}
	
	public EntityModel<DeliveryAddressResponse> toModel(DeliveryAddressResponse entity, String apiContext) 
	{
		EntityModel<DeliveryAddressResponse> entityModel;
		switch (apiContext) {
		case "GetOne": 
						{
							entityModel = EntityModel.of(entity,
									linkTo(methodOn(DeliveryAddressController.class).retrieveCustomerAddress(entity.getCustomerResponseId(), entity.getId())).withSelfRel(),
									linkTo(methodOn(DeliveryAddressController.class).UpdateAddress(entity.getCustomerResponseId(), entity.getId(), null)).withRel("update"),
									linkTo(methodOn(DeliveryAddressController.class).deleteAddress(entity.getCustomerResponseId(), entity.getId())).withRel("delete"),
									linkTo(methodOn(DeliveryAddressController.class).retrieveAllCustomerAddress(entity.getCustomerResponseId())).withRel("user_addresses"));
							return entityModel;
						}
		case "update":
						{
							entityModel = EntityModel.of(entity,
									linkTo(methodOn(DeliveryAddressController.class).retrieveCustomerAddress(entity.getCustomerResponseId(), entity.getId())).withSelfRel(),
									linkTo(methodOn(DeliveryAddressController.class).deleteAddress(entity.getCustomerResponseId(), entity.getId())).withRel("delete"),
									linkTo(methodOn(DeliveryAddressController.class).retrieveAllCustomerAddress(entity.getCustomerResponseId())).withRel("user_addresses"));
							return entityModel;
						}
		case "delete":
						{
							entityModel = EntityModel.of(entity,
									linkTo(methodOn(DeliveryAddressController.class).addAddress(null, entity.getCustomerResponseId())).withRel("create"),
									linkTo(methodOn(DeliveryAddressController.class).retrieveAllCustomerAddress(entity.getCustomerResponseId())).withRel("all_customers"));
							return entityModel;
						}
		default:
						throw new IllegalArgumentException("Unexpected key: " + apiContext);
		}
	}
}
