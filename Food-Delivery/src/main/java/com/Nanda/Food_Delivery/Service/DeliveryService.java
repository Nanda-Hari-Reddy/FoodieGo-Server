package com.Nanda.Food_Delivery.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.Nanda.Food_Delivery.Model.Delivery;
import com.Nanda.Food_Delivery.Model.DeliveryAddress;
import com.Nanda.Food_Delivery.Model.DeliveryAddressEmbedded;
import com.Nanda.Food_Delivery.Model.DeliveryPerson;
import com.Nanda.Food_Delivery.Model.Order_Entity;
import com.Nanda.Food_Delivery.Repository.DeliveryRepository;
import com.Nanda.Food_Delivery.Transformer.DeliveryAddressTransformer;
import com.Nanda.Food_Delivery.Transformer.DeliveryTransformer;
import com.Nanda.Food_Delivery.dtoResponse.DeliveryResponse;

@Service
public class DeliveryService
{
	DeliveryRepository deliveryRepository;
	OrderService orderService;
	DeliveryPersonService deliveryPersonService;
	
	public DeliveryService(DeliveryRepository deliveryRepository, OrderService orderService,
			DeliveryPersonService deliveryPersonService) {
		super();
		this.deliveryRepository = deliveryRepository;
		this.orderService = orderService;
		this.deliveryPersonService = deliveryPersonService;
	}

	public DeliveryResponse placingOrder(int orderId)
	{
		Order_Entity order_entity =  orderService.findOrder_Entity(orderId);
		Optional<Delivery> isdelivery =  deliveryRepository.findByOrder(order_entity);
		if(!isdelivery.isEmpty()) return DeliveryTransformer.entityToResponse(isdelivery.get());
		String customerName = order_entity.getCustomer().getName();
		String customerEmail = order_entity.getCustomer().getEmail();
		double cost = order_entity.getTotalCost();
		String restaurantName = order_entity.getRestaurant().getRestaurantName();
		String deliveryTime = "45 min";
		DeliveryPerson deliveryPerson = deliveryPersonService.getRandomDeliveryPerson();
		DeliveryAddress address = order_entity.getCustomer().getAddresses().get(0);
		DeliveryAddressEmbedded embedded = DeliveryAddressTransformer.addrsstoEmbedded(address);
		Delivery delivery = Delivery.builder()
							.order(order_entity)
							.customerName(customerName)
							.customerEmail(customerEmail)
							.cost(cost)
							.restaurantName(restaurantName)
							.deliveryTime(deliveryTime)
							.deliveryPerson(deliveryPerson)
							.addressEmbedded(embedded)
							.build();
		deliveryPerson.getDeliveries().add(delivery);
		deliveryRepository.save(delivery);
		return DeliveryTransformer.entityToResponse(delivery);
	}
	
	public List<DeliveryResponse> deliveriesForDeliveryPerson(int deliveryPersonId)
	{
		List<Delivery> deliveries = deliveryRepository.findByDeliveryPersonId(deliveryPersonId);
		List<DeliveryResponse> deliveryResponses = deliveries.stream()
													.map(delivery -> DeliveryTransformer.entityToResponse(delivery))
													.collect(Collectors.toList());
		return deliveryResponses;
	}

	public void deleteDelivery(int deliveryId) 
	{

		deliveryRepository.deleteById(deliveryId);
	}

	public List<DeliveryResponse> getDeliveriesforUser(String email) 
	{
		Function<Delivery, DeliveryResponse> entityToresponse = delivery -> DeliveryTransformer.entityToResponse(delivery);
		List<Delivery> deliveries =  deliveryRepository.findByCustomerEmail(email);
		
		return deliveries.stream()
				.map(entityToresponse)
				.collect(Collectors.toList());
	}
}
