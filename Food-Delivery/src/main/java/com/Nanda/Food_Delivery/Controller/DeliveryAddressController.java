package com.Nanda.Food_Delivery.Controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.Nanda.Food_Delivery.Service.DeliveryAddressService;
import com.Nanda.Food_Delivery.dtoRequests.DeliveryAddressRequest;
import com.Nanda.Food_Delivery.dtoResponse.DeliveryAddressResponse;
import com.Nanda.Food_Delivery.exception.ErrorDetails;
import com.Nanda.Food_Delivery.hateoas.DeliveryAddressModelAssembler;

@RestController
@RequestMapping("/user/{userId}/address")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class DeliveryAddressController
{
	DeliveryAddressService deliveryAddressService;
	DeliveryAddressModelAssembler modelAssembler;
	ErrorDetails errorDetails;
	
	public DeliveryAddressController(DeliveryAddressService deliveryAddressService,
			DeliveryAddressModelAssembler modelAssembler, ErrorDetails errorDetails) 
	{
		this.deliveryAddressService = deliveryAddressService;
		this.modelAssembler = modelAssembler;
		this.errorDetails = errorDetails;
		errorDetails.setMessage("No requested body found");
		errorDetails.setTimestamp(LocalDateTime.now());
	}
	
	
	@PostMapping
	public ResponseEntity<DeliveryAddressResponse> addAddress
								(@RequestBody DeliveryAddressRequest addressRequest, @PathVariable int userId)
	{
		if(addressRequest==null) return new ResponseEntity(errorDetails, HttpStatus.NOT_ACCEPTABLE);
		DeliveryAddressResponse addressResponse = deliveryAddressService
													.addDeliveryAddress(addressRequest, userId);
		if(addressResponse!=null)
		{
			URI location = ServletUriComponentsBuilder
						   .fromCurrentRequest()
						   .path("/{deliveryId}")
						   .buildAndExpand(addressResponse.getId())
						   .toUri();
			return ResponseEntity.created(location).build();
		}
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}
	@GetMapping
	public ResponseEntity<CollectionModel<EntityModel<DeliveryAddressResponse>>> retrieveAllCustomerAddress(@PathVariable int userId)
	{
		List<DeliveryAddressResponse> addressResponses = deliveryAddressService.findAllAddressesOfCustomer(userId);
		List<EntityModel<DeliveryAddressResponse>> entityModels = addressResponses.stream()
				.map(customerresponse -> modelAssembler.toModel(customerresponse, "GetOne"))
				.collect(Collectors.toList());
		CollectionModel<EntityModel<DeliveryAddressResponse>> entityModelCollection = CollectionModel.of(entityModels, linkTo(methodOn(DeliveryAddressController.class).retrieveAllCustomerAddress(userId)).withSelfRel());
		return new ResponseEntity<>(entityModelCollection, HttpStatus.ACCEPTED);
}

	@GetMapping(path = "/{addressId}")
	public ResponseEntity<EntityModel<DeliveryAddressResponse>> retrieveCustomerAddress(@PathVariable int userId, @PathVariable int addressId)
	{
		DeliveryAddressResponse addressResponse = deliveryAddressService.findAddressById(addressId);
		EntityModel<DeliveryAddressResponse> entityModel = modelAssembler.toModel(addressResponse, "GetOne");
		return new ResponseEntity<>(entityModel, HttpStatus.ACCEPTED);
	}
	
	@PutMapping(path = "/{addressId}")
	public ResponseEntity<EntityModel<DeliveryAddressResponse>> UpdateAddress(@PathVariable int userId, @PathVariable int addressId, @RequestBody DeliveryAddressRequest deliveryAddressRequest)
	{
		if(deliveryAddressRequest==null) return new ResponseEntity(errorDetails, HttpStatus.NOT_ACCEPTABLE);
		DeliveryAddressResponse deliveryAddressResponse = deliveryAddressService.updateDeliveryAddress(addressId, deliveryAddressRequest);
		EntityModel<DeliveryAddressResponse> entityModel = modelAssembler.toModel(deliveryAddressResponse, "update");
		return ResponseEntity
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
	}
	
	@DeleteMapping(params = "addressId")
	public ResponseEntity<EntityModel<DeliveryAddressResponse>> deleteAddress(@PathVariable int userId, @RequestParam int addressId)
	{
		System.out.println("Address id "+ addressId);
		DeliveryAddressResponse deliveryAddressResponse = deliveryAddressService.deleteDeliveryAddress(addressId);
		EntityModel<DeliveryAddressResponse> entityModel = modelAssembler.toModel(deliveryAddressResponse, "delete");
		return new ResponseEntity<>(entityModel, HttpStatus.ACCEPTED);
	}
}
