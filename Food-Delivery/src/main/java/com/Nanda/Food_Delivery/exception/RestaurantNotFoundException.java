package com.Nanda.Food_Delivery.exception;

public class RestaurantNotFoundException extends RuntimeException
{
	public RestaurantNotFoundException(String message)
	{
		super(message);
	}
}
