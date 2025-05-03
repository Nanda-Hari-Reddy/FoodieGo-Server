package com.Nanda.Food_Delivery.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Nanda.Food_Delivery.Model.DeliveryPerson;
import com.Nanda.Food_Delivery.Repository.DeliveryPersonRepository;
import com.Nanda.Food_Delivery.Transformer.DeliveryPersonTransformer;
import com.Nanda.Food_Delivery.dtoRequests.DeliveryPersonRequest;
import com.Nanda.Food_Delivery.dtoResponse.DeliveryPersonResponse;
import com.Nanda.Food_Delivery.exception.DeliveryPersonNotFoundException;

@Service
public class DeliveryPersonService
{
	@Autowired
	DeliveryPersonRepository deliveryPersonRepository;
	
	public DeliveryPersonResponse addDeliveryPerson(DeliveryPersonRequest deliveryPersonRequest)
	{
		DeliveryPerson deliveryPerson = DeliveryPersonTransformer
										.requestToEntity(deliveryPersonRequest);
		DeliveryPerson deliveryPersonSaved = deliveryPersonRepository.save(deliveryPerson);
		return DeliveryPersonTransformer.entityToResponse(deliveryPersonSaved);
	}
	
	public DeliveryPersonResponse getById(int id)
	{
		Optional<DeliveryPerson> dlvyPsn = deliveryPersonRepository.findById(id);
		if(dlvyPsn.isEmpty()) throw new DeliveryPersonNotFoundException("no Delivery person found with id "+id);
		DeliveryPerson deliveryPerson = dlvyPsn.get();
		return DeliveryPersonTransformer.entityToResponse(deliveryPerson);
	}
	
	public List<DeliveryPersonResponse> getAllDeliveryPerson()
	{
		List<DeliveryPerson> deliveryGuys = deliveryPersonRepository.findAll();
		if(deliveryGuys==null) throw new DeliveryPersonNotFoundException("no Delivery persons found");
		Function<DeliveryPerson, DeliveryPersonResponse> personsToResponses = 
								person -> DeliveryPersonTransformer.entityToResponse(person);
		List<DeliveryPersonResponse> personResponses  = deliveryGuys.stream()
														.map(personsToResponses)
														.collect(Collectors.toList());
		return personResponses;
	}
	
	public DeliveryPerson getRandomDeliveryPerson()
	{
		DeliveryPerson deliveryPerson = deliveryPersonRepository.findRandomEntity();
		return deliveryPerson;
	}

	public DeliveryPersonResponse updateDeliveryPerson(int deliveryPersonId,
			DeliveryPersonRequest deliveryPersonRequest) 
	{
		DeliveryPerson deliveryPerson = DeliveryPersonTransformer.requestToEntity(deliveryPersonRequest);
		deliveryPerson.setId(deliveryPersonId);
		Optional<DeliveryPerson> dlvyPsn = deliveryPersonRepository.findById(deliveryPersonId);
		
		if(dlvyPsn.isEmpty()) throw new DeliveryPersonNotFoundException("no Delivery person found with id "+deliveryPersonId);
		deliveryPersonRepository.save(deliveryPerson);
		DeliveryPersonResponse deliveryPersonResponse = DeliveryPersonTransformer.entityToResponse(deliveryPerson);
		return deliveryPersonResponse;
	}

	public DeliveryPersonResponse deleteDeliveryPerson(int deliveryPersonId) 
	{
		Optional<DeliveryPerson> deliveryPerson = deliveryPersonRepository.findById(deliveryPersonId);
		
		if(deliveryPerson.isEmpty()) throw new DeliveryPersonNotFoundException("no Delivery person found with id "+deliveryPersonId);
		deliveryPersonRepository.deleteById(deliveryPersonId);
		DeliveryPersonResponse deliveryPersonResponse = DeliveryPersonTransformer.entityToResponse(deliveryPerson.get());
		return deliveryPersonResponse;
	}
	
}
