package com.Nanda.Food_Delivery.hateoas;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.Nanda.Food_Delivery.Controller.CustomerController;
import com.Nanda.Food_Delivery.Controller.DeliveryAddressController;
import com.Nanda.Food_Delivery.Model.Customer;
import com.Nanda.Food_Delivery.dtoResponse.CustomerResponse;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@Component
public class CustomerModelAssembler extends RepresentationModelAssemblerSupport<CustomerResponse, EntityModel<CustomerResponse>>
{

	@SuppressWarnings("unchecked")
	public CustomerModelAssembler(Class<?> controllerClass, Class<EntityModel<Customer>> resourceType) 
	{
		super(CustomerController.class, (Class<EntityModel<CustomerResponse>>) (Class<?>) EntityModel.class);
	}
	
	@Override
	public EntityModel<CustomerResponse> toModel(CustomerResponse entity) 
	{		
		return null;
	
	}
	
	public EntityModel<CustomerResponse> toModel(CustomerResponse customerResponse, String apiContext) 
	{
		EntityModel<CustomerResponse> entityModel;
		switch (apiContext) {
		case "GetOne": 
						{
							entityModel = EntityModel.of(customerResponse,
									linkTo(methodOn(CustomerController.class).retrieveCustomer(customerResponse.getEmail())).withSelfRel(),
									linkTo(methodOn(CustomerController.class).UpdateCustomer(customerResponse.getId(), null)).withRel("update"),
									linkTo(methodOn(CustomerController.class).deleteCustomer(customerResponse.getId())).withRel("delete"),
									linkTo(methodOn(CustomerController.class).retrieveAllCustomers()).withRel("all_customers"),
									linkTo(methodOn(DeliveryAddressController.class).retrieveAllCustomerAddress(customerResponse.getId())).withRel("relation"));
							return entityModel;
						}
		case "update","create":
						{
							entityModel = EntityModel.of(customerResponse,
									linkTo(methodOn(CustomerController.class).retrieveCustomer(customerResponse.getEmail())).withSelfRel(),  
									linkTo(methodOn(CustomerController.class).deleteCustomer(customerResponse.getId())).withRel("delete"),
									linkTo(methodOn(CustomerController.class).retrieveAllCustomers()).withRel("all_customers"));
							return entityModel;
						}
		case "delete":
						{
							entityModel = EntityModel.of(customerResponse,
									linkTo(methodOn(CustomerController.class).addCustomer(null)).withRel("create"),
									linkTo(methodOn(CustomerController.class).retrieveAllCustomers()).withRel("all_customers"));
							return entityModel;
						}
		default:
						throw new IllegalArgumentException("Unexpected key: " + apiContext);
		}
	}
}
