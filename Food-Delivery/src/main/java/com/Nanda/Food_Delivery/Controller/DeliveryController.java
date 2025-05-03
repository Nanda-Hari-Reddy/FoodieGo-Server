package com.Nanda.Food_Delivery.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Nanda.Food_Delivery.Service.DeliveryService;
import com.Nanda.Food_Delivery.dtoResponse.DeliveryResponse;



@RestController
public class DeliveryController 
{
	DeliveryService deliveryService;
	
	public DeliveryController(DeliveryService deliveryService) {
		this.deliveryService = deliveryService;
	}

	@PostMapping(path = "/placeOrder")
	public ResponseEntity<DeliveryResponse> placeOrder(@RequestParam Integer orderId)
	{
		
		DeliveryResponse deliveryResponse = deliveryService.placingOrder(orderId);
		System.out.println("*****************************************************");
		return new ResponseEntity<>(deliveryResponse, HttpStatus.CREATED);
	}
	
	@GetMapping(path = "/getDeliveries")
	public ResponseEntity<List<DeliveryResponse>> getDeliveriesforUser(@RequestParam String email)
	{
		
		List<DeliveryResponse> deliveryResponse = deliveryService.getDeliveriesforUser(email);
		return new ResponseEntity<>(deliveryResponse, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{deliveryId}")
	public void deleteDelivery(@PathVariable int deliveryId)
	{
		
		deliveryService.deleteDelivery(deliveryId);
	}
}
