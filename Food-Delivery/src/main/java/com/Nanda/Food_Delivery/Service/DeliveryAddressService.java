package com.Nanda.Food_Delivery.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.Nanda.Food_Delivery.Model.Customer;
import com.Nanda.Food_Delivery.Model.DeliveryAddress;
import com.Nanda.Food_Delivery.Repository.CustomerRepository;
import com.Nanda.Food_Delivery.Repository.DeliveyAddressRepository;
import com.Nanda.Food_Delivery.Transformer.DeliveryAddressTransformer;
import com.Nanda.Food_Delivery.dtoRequests.DeliveryAddressRequest;
import com.Nanda.Food_Delivery.dtoResponse.DeliveryAddressResponse;
import com.Nanda.Food_Delivery.exception.DeliveryAddressNotFoundException;
import com.Nanda.Food_Delivery.exception.UserNotFoundException;

@Service
public class DeliveryAddressService
{
	DeliveyAddressRepository addressRepository;
	CustomerRepository customerRepository;
	
	public DeliveryAddressService(DeliveyAddressRepository addressRepository, CustomerRepository customerRepository) {
		this.addressRepository = addressRepository;
		this.customerRepository = customerRepository;
	}

	public DeliveryAddressResponse addDeliveryAddress(DeliveryAddressRequest delivAddressRequest, int customerId)
	{
		DeliveryAddress deliveryAddress = DeliveryAddressTransformer
									      .requestToEntity(delivAddressRequest);
		Optional<Customer> customer = customerRepository.findById(customerId);
		if(customer.isEmpty()) throw new UserNotFoundException("No user Found");
		deliveryAddress.setCustomer(customer.get());
		addressRepository.save(deliveryAddress);
		return findAddressById(deliveryAddress.getId());
	}

	public DeliveryAddressResponse findAddressById(Integer addressId)
	{
		Optional<DeliveryAddress> address = addressRepository.findById(addressId);
		if(address.isEmpty()) throw new DeliveryAddressNotFoundException("No Address Found");
		DeliveryAddressResponse addressResponse = DeliveryAddressTransformer
												  .entityToResponse(address.get());
		return addressResponse;
	}

	public List<DeliveryAddressResponse> findAllAddressesOfCustomer(int customeerId)
	{
		List<DeliveryAddress> customerAddresses = addressRepository
												  .findAllByCustomerId(customeerId);
		if(customerAddresses==null) return null;
		Function<DeliveryAddress, DeliveryAddressResponse> addressToresponse =
									address -> DeliveryAddressTransformer
									  .entityToResponse(address);
		List<DeliveryAddressResponse> addressResponses = customerAddresses.stream()
												.map(addressToresponse)
												.collect(Collectors.toList());
		return addressResponses;

	}

	public DeliveryAddressResponse updateDeliveryAddress(int addressId, DeliveryAddressRequest deliveryAddressRequest) 
	{
		DeliveryAddress deliveryAddress = DeliveryAddressTransformer.requestToEntity(deliveryAddressRequest);
		deliveryAddress.setId(addressId);
		Optional<DeliveryAddress> Address = addressRepository.findById(addressId);
		if(Address.isEmpty()) throw new DeliveryAddressNotFoundException("No address Found");
		Customer customer = Address.get().getCustomer();
		deliveryAddress.setCustomer(customer);
		addressRepository.save(deliveryAddress);
		DeliveryAddressResponse deliveryAddressResponse = DeliveryAddressTransformer.entityToResponse(deliveryAddress);
		return deliveryAddressResponse;
	}

	public DeliveryAddressResponse deleteDeliveryAddress(int addressId) 
	{
		Optional<DeliveryAddress> address = addressRepository.findById(addressId);
		
		if(address.isEmpty()) throw new DeliveryAddressNotFoundException("No address Found");
		addressRepository.deleteById(addressId);
		DeliveryAddressResponse deliveryAddressResponse = DeliveryAddressTransformer.entityToResponse(address.get());
		return deliveryAddressResponse;
	}
}
