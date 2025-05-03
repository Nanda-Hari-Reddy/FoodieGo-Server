package com.Nanda.Food_Delivery.Transformer;

import com.Nanda.Food_Delivery.Model.DeliveryPerson;
import com.Nanda.Food_Delivery.dtoRequests.DeliveryPersonRequest;
import com.Nanda.Food_Delivery.dtoResponse.DeliveryPersonResponse;

public class DeliveryPersonTransformer 
{
	public static DeliveryPerson requestToEntity
										(DeliveryPersonRequest request)
	{
		return DeliveryPerson.builder()
										.name(request.getName())
										.age(request.getAge())
										.email(request.getEmail())
										.password(request.getPassword())
										.gender(request.getGender())
										.phoneNo(request.getPhoneNo())
										.build();
	}
	
	public static DeliveryPersonResponse entityToResponse
												(DeliveryPerson entity)
	{
		
		return DeliveryPersonResponse.builder()
				.id(entity.getId())
				.name(entity.getName())
				.email(entity.getEmail())
				.gender(entity.getGender())
				.phoneNo(entity.getPhoneNo())
				.build();
	}
}
