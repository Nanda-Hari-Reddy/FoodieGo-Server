package com.Nanda.Food_Delivery.Transformer;

import com.Nanda.Food_Delivery.Model.DeliveryAddress;
import com.Nanda.Food_Delivery.Model.DeliveryAddressEmbedded;
import com.Nanda.Food_Delivery.dtoRequests.DeliveryAddressRequest;
import com.Nanda.Food_Delivery.dtoResponse.DeliveryAddressResponse;

public class DeliveryAddressTransformer
{
	public static DeliveryAddress requestToEntity(DeliveryAddressRequest request)
	{
		DeliveryAddress address = DeliveryAddress.builder()
							      .street(request.getStreet())
							      .area(request.getArea())
							      .city(request.getCity())
							      .build();
		return address;
	}

	public static DeliveryAddressResponse entityToResponse(DeliveryAddress entity)
	{
		DeliveryAddressResponse addressResponse = DeliveryAddressResponse.builder()
								  .id(entity.getId())
							      .street(entity.getStreet())
							      .area(entity.getArea())
							      .city(entity.getCity())
							      .customerResponseId(entity.getCustomer().getId())
							      .build();
		return addressResponse;
	}
	
	public static DeliveryAddressEmbedded addrsstoEmbedded(DeliveryAddress address)
	{
		DeliveryAddressEmbedded embedded = DeliveryAddressEmbedded.builder()
										   .street(address.getStreet())
										   .area(address.getArea())
										   .city(address.getCity())
										   .build();
		return embedded;
	}
}
