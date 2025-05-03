package com.Nanda.Food_Delivery.exception;

public class DeliveryAddressNotFoundException extends RuntimeException
{
	public DeliveryAddressNotFoundException(String message)
	{
		super(message);
	}
}
