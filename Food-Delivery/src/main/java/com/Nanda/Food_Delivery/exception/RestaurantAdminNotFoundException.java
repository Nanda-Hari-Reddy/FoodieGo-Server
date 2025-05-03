package com.Nanda.Food_Delivery.exception;

public class RestaurantAdminNotFoundException extends RuntimeException
{
	public RestaurantAdminNotFoundException(String message)
	{
		super(message);
	}
}
