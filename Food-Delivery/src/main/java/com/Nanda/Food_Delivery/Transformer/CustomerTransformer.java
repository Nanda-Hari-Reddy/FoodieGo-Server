package com.Nanda.Food_Delivery.Transformer;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.Nanda.Food_Delivery.Model.Customer;
import com.Nanda.Food_Delivery.dtoRequests.CustomerRequest;
import com.Nanda.Food_Delivery.dtoResponse.CustomerResponse;

public class CustomerTransformer
{
	public static Customer requestToEntity(CustomerRequest request, String password)
	{
		Customer customer = Customer.builder()
							.id(request.getId())
							.name(request.getName())
							.email(request.getEmail())
							.imageURL(request.getImageURL())
							.password(password)
							.gender(request.getGender())
							.mobileNo(request.getMobileNo())
							.build();
		return customer;
	}

	public static CustomerResponse entityToResponse(Customer entity)
	{
		CustomerResponse customerResponse = CustomerResponse.builder()
							.id(entity.getId())
							.name(entity.getName())
							.email(entity.getEmail())
							.addressResponse(entity.getAddresses()
											.stream()
											.map(address -> DeliveryAddressTransformer.entityToResponse(address))
											.collect(Collectors
											.toList()))
							.gender(entity.getGender())
							.mobileNo(entity.getMobileNo())
							.imageURL(entity.getImageURL())
							.cartResponse(CartTransformer.entityToResponse(entity.getCart()))
							.build();
		return customerResponse;
	}

}
