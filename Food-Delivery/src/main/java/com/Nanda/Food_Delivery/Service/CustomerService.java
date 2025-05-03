package com.Nanda.Food_Delivery.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Nanda.Food_Delivery.Model.Cart;
import com.Nanda.Food_Delivery.Model.Customer;
import com.Nanda.Food_Delivery.Repository.CustomerRepository;
import com.Nanda.Food_Delivery.Transformer.CustomerTransformer;
import com.Nanda.Food_Delivery.dtoRequests.CustomerRequest;
import com.Nanda.Food_Delivery.dtoResponse.CustomerResponse;
import com.Nanda.Food_Delivery.exception.UserNotFoundException;

@Service
public class CustomerService
{
	private CustomerRepository customerRepository;
	private DeliveryAddressService addressService;
	private PasswordEncoder passwordEncoder;
	public CustomerService(CustomerRepository customerRepository, DeliveryAddressService addressService, PasswordEncoder passwordEncoder) {
		super();
		this.customerRepository = customerRepository;
		this.addressService = addressService;
		this.passwordEncoder = passwordEncoder;
	}

	public CustomerResponse addCustomer(CustomerRequest customerRequest)
	{
		String password = passwordEncoder.encode(customerRequest.getPassword());
		Customer customer = CustomerTransformer
							.requestToEntity(customerRequest, password);
		Cart cart = new Cart();
		customer.setCart(cart);
		cart.setCustomer(customer);
		customerRepository.save(customer);
		addressService.addDeliveryAddress(customerRequest.getAddressRequest(), customer.getId());
		return findCustomerById(customer.getId());
	}

	public CustomerResponse findCustomerById(int customerId)
	{
		Optional<Customer> customer = customerRepository.findById(customerId);
		if(customer.isEmpty()) throw new UserNotFoundException("No user found with id "+customerId);
		Customer ctmr = customer.get();
		CustomerResponse customerResponse = CustomerTransformer
											.entityToResponse(ctmr);
		return customerResponse;
	}
	
	public CustomerResponse findCustomerByEmail(String email)
	{
		Optional<Customer> customer = customerRepository.findByEmail(email);
		if(customer.isEmpty()) throw new UserNotFoundException("No user found with email "+email);
		Customer ctmr = customer.get();
		CustomerResponse customerResponse = CustomerTransformer
											.entityToResponse(ctmr);
		return customerResponse;
	}

	public List<CustomerResponse> findAllCustomers()
	{
		List<Customer> customerList = customerRepository.findAll();
		if(customerList.isEmpty()) return null;
		
		Function<Customer, CustomerResponse> customerToCustomerResponse =
				customer -> CustomerTransformer.entityToResponse(customer);

		List<CustomerResponse> customerResponseList =  customerList.stream()
													   .map(customerToCustomerResponse)
													   .collect(Collectors.toList());
		return customerResponseList;
	}
	
	public CustomerResponse updateCustomer(int customerId, CustomerRequest customerRequest)
	{
		
		Optional<Customer> ctmr = customerRepository.findById(customerId);
		String password = passwordEncoder.encode(ctmr.get().getPassword());
		Customer customer = CustomerTransformer.requestToEntity(customerRequest, password);
		customer.setId(customerId);
		if(ctmr.isEmpty()) throw new UserNotFoundException("No user found with id "+customerId);
		customer.setPassword(ctmr.get().getPassword());
		customerRepository.save(customer);
		CustomerResponse customerResponse = CustomerTransformer.entityToResponse(customer);
		return customerResponse;
	}

	public CustomerResponse deleteCustomer(int customerId) 
	{
		Optional<Customer> customer = customerRepository.findById(customerId);
		if(customer.isEmpty()) throw new UserNotFoundException("No user found with id "+customerId);
		customerRepository.deleteById(customerId);
		CustomerResponse customerResponse = CustomerTransformer.entityToResponse(customer.get());
		return customerResponse;
	}

	public boolean updateProfilePic(int userId, String imageURL) 
	{
		Optional<Customer> customer =  customerRepository.findById(userId);
		if(customer.isEmpty()) throw new UserNotFoundException("No user found with id "+ userId);
		Customer ctmr = customer.get();
		ctmr.setImageURL(imageURL);
		customerRepository.save(ctmr);
		return true;
	}
}
