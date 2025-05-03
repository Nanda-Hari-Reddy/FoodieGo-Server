package com.Nanda.Food_Delivery.Controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.Nanda.Food_Delivery.Service.CustomerService;
import com.Nanda.Food_Delivery.dtoRequests.CustomerRequest;
import com.Nanda.Food_Delivery.dtoResponse.CustomerResponse;
import com.Nanda.Food_Delivery.exception.ErrorDetails;
import com.Nanda.Food_Delivery.hateoas.CustomerModelAssembler;


import org.springframework.web.bind.annotation.PutMapping;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;




@RestController
@RequestMapping("/user")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CustomerController
{
	CustomerService customerService;
	CustomerModelAssembler customerModelAssembler;
	ErrorDetails errorDetails;
	
	public CustomerController(CustomerService customerService, CustomerModelAssembler customerModelAssembler,
			ErrorDetails errorDetails) {
		super();
		this.customerService = customerService;
		this.customerModelAssembler = customerModelAssembler;
		this.errorDetails = errorDetails;
		errorDetails.setMessage("No requested body found");
		errorDetails.setTimestamp(LocalDateTime.now());
	}

	@PostMapping
	public ResponseEntity<CustomerResponse> addCustomer
										(@RequestBody CustomerRequest customerRequest)
	{
		if(customerRequest==null) return new ResponseEntity(errorDetails, HttpStatus.NOT_ACCEPTABLE);
		CustomerResponse customerResponse = customerService.addCustomer(customerRequest);
		URI location = ServletUriComponentsBuilder
					   .fromCurrentRequest()
					   .path("/{customerId}")
					   .buildAndExpand(customerResponse.getId())
					   .toUri();
		return ResponseEntity.created(location).build();
	}

	@GetMapping
	public ResponseEntity<CollectionModel<EntityModel<CustomerResponse>>> retrieveAllCustomers()
	{
		List<CustomerResponse> customerResponses = customerService.findAllCustomers();
		List<EntityModel<CustomerResponse>> entityModels = customerResponses.stream()
															.map(customerresponse -> customerModelAssembler.toModel(customerresponse, "GetOne"))
															.collect(Collectors.toList());
		CollectionModel<EntityModel<CustomerResponse>> entityModelCollection = CollectionModel.of(entityModels, linkTo(methodOn(CustomerController.class).retrieveAllCustomers()).withSelfRel());
		return new ResponseEntity<>(entityModelCollection, HttpStatus.ACCEPTED);
	}

	@GetMapping(path = "/getUser")
	public ResponseEntity<EntityModel<CustomerResponse>> retrieveCustomer(@RequestParam String email)
	{
		CustomerResponse customerResponse = customerService.findCustomerByEmail(email);
		EntityModel<CustomerResponse> entityModel = customerModelAssembler.toModel(customerResponse, "GetOne");
		return new ResponseEntity<>(entityModel, HttpStatus.ACCEPTED);
	}
	
	@PutMapping(path = "{userId}")
	public ResponseEntity<EntityModel<CustomerResponse>>  UpdateCustomer(@PathVariable int userId, @RequestBody CustomerRequest customerRequest) 
	{
		System.out.println(customerRequest+"nfnffffffff");
		if(customerRequest==null) return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		CustomerResponse customerResponse = customerService.updateCustomer(userId, customerRequest);
		EntityModel<CustomerResponse> entityModel = customerModelAssembler.toModel(customerResponse, "update");
		return ResponseEntity
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
	}
	
	@DeleteMapping(path = "{userId}")
	public ResponseEntity<EntityModel<CustomerResponse>> deleteCustomer(@PathVariable int userId)
	{
		CustomerResponse customerResponse = customerService.deleteCustomer(userId);
		EntityModel<CustomerResponse> entityModel = customerModelAssembler.toModel(customerResponse, "delete");
		return new ResponseEntity<>(entityModel, HttpStatus.ACCEPTED);
	}
	
	@PatchMapping(path = "/{userId}")
	public ResponseEntity<Void> updateProfilePic(@PathVariable int userId, @RequestBody Map<String, String> payload) {
	    String imageURL = payload.get("imageURL");
	    System.out.println(payload);
	    boolean flag = customerService.updateProfilePic(userId, imageURL);
	    if (!flag) {
	        return ResponseEntity.internalServerError().build();
	    }
	    return ResponseEntity.ok().build();
	}

}
