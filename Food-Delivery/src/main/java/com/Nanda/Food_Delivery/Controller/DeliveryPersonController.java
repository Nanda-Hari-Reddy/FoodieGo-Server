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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.Nanda.Food_Delivery.Service.DeliveryPersonService;
import com.Nanda.Food_Delivery.Service.DeliveryService;
import com.Nanda.Food_Delivery.dtoRequests.DeliveryPersonRequest;
import com.Nanda.Food_Delivery.dtoResponse.DeliveryPersonResponse;
import com.Nanda.Food_Delivery.dtoResponse.DeliveryResponse;
import com.Nanda.Food_Delivery.exception.ErrorDetails;
import com.Nanda.Food_Delivery.hateoas.DeliveryPersonModelAssembler;



//import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/deliveryPerson")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class DeliveryPersonController
{
	DeliveryPersonService deliveryPersonService;
	DeliveryService deliveryService;
	DeliveryPersonModelAssembler modelAssembler;
	ErrorDetails errorDetails;
	public DeliveryPersonController(DeliveryPersonService deliveryPersonService, DeliveryService deliveryService,
			DeliveryPersonModelAssembler modelAssembler, ErrorDetails errorDetails) 
	{
		this.deliveryPersonService = deliveryPersonService;
		this.deliveryService = deliveryService;
		this.modelAssembler = modelAssembler;
		this.errorDetails = errorDetails;
		errorDetails.setMessage("No requested body found");
		errorDetails.setTimestamp(LocalDateTime.now());
	}

	@PostMapping
	public ResponseEntity<DeliveryPersonResponse> addDeliveyPerson(@RequestBody DeliveryPersonRequest deliveryPersonRequest)
	{	
		if(deliveryPersonRequest==null) return new ResponseEntity(errorDetails, HttpStatus.NOT_ACCEPTABLE);

		DeliveryPersonResponse deliveryPersonReesponse = deliveryPersonService.addDeliveryPerson(deliveryPersonRequest);
		URI location = ServletUriComponentsBuilder
					   .fromCurrentRequest()
					   .path("/{deliveryPersonId}")
					   .buildAndExpand(deliveryPersonReesponse.getId())
					   .toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping(path = "/{deliveryPersonId}")
	public ResponseEntity<EntityModel<DeliveryPersonResponse>> retrieveDeliveryPerson(@PathVariable int deliveryPersonId)
	{
		DeliveryPersonResponse deliveryPersonResponse = deliveryPersonService.getById(deliveryPersonId);
		EntityModel<DeliveryPersonResponse> entityModel = modelAssembler.toModel(deliveryPersonResponse, "GetOne");
		return new ResponseEntity<>(entityModel, HttpStatus.ACCEPTED);
	}
	
	@GetMapping
	public ResponseEntity<CollectionModel<EntityModel<DeliveryPersonResponse>>> retrieveAllDeliveryPerson()
	{
		List<DeliveryPersonResponse> deliveryPersonResponseList = deliveryPersonService.getAllDeliveryPerson();
		List<EntityModel<DeliveryPersonResponse>> entityModels = deliveryPersonResponseList.stream()
				.map(deliveryPersonResponse -> modelAssembler.toModel(deliveryPersonResponse, "GetOne"))
				.collect(Collectors.toList());
		CollectionModel<EntityModel<DeliveryPersonResponse>> entityModelCollection = CollectionModel.of(entityModels, linkTo(methodOn(DeliveryPersonController.class).retrieveAllDeliveryPerson()).withSelfRel());
		return new ResponseEntity<>(entityModelCollection, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{deliveryPersonId}/myDeliveries")
	public ResponseEntity<List<DeliveryResponse>> deliveriesAssained(@PathVariable int deliveryPersonId) 
	{
		List<DeliveryResponse> deliveryResponses = deliveryService.deliveriesForDeliveryPerson(deliveryPersonId);
		
		return new ResponseEntity<>(deliveryResponses, HttpStatus.ACCEPTED);
	}
	
	@PutMapping(path = "/{deliveryPersonId}")
	public ResponseEntity<EntityModel<DeliveryPersonResponse>> UpdateDeliveryPerson(@PathVariable int deliveryPersonId, @RequestBody DeliveryPersonRequest deliveryPersonRequest) 
	{
		if(deliveryPersonRequest==null) return new ResponseEntity(errorDetails, HttpStatus.NOT_ACCEPTABLE);

		DeliveryPersonResponse deliveryPersonResponse = deliveryPersonService.updateDeliveryPerson(deliveryPersonId, deliveryPersonRequest);
		EntityModel<DeliveryPersonResponse> entityModel = modelAssembler.toModel(deliveryPersonResponse, "update");
		return ResponseEntity
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
	}
	
	@DeleteMapping(path = "/{deliveryPersonId}")
	public ResponseEntity<EntityModel<DeliveryPersonResponse>> deleteDeliveryPerson(@PathVariable int deliveryPersonId)
	{
		DeliveryPersonResponse deliveryPersonResponse = deliveryPersonService.deleteDeliveryPerson(deliveryPersonId);
		EntityModel<DeliveryPersonResponse> entityModel = modelAssembler.toModel(deliveryPersonResponse, "delete");
		return new ResponseEntity<>(entityModel, HttpStatus.ACCEPTED);
	}
}

