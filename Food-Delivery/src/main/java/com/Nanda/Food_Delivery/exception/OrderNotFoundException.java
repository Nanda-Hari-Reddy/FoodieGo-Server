package com.Nanda.Food_Delivery.exception;

public class OrderNotFoundException extends RuntimeException
{
	public OrderNotFoundException(String message)
	{
		super(message);
	}
}
