package com.Nanda.Food_Delivery.exception;

public class FoodItemNotFoundException extends RuntimeException
{
	public FoodItemNotFoundException(String message)
	{
		super(message);
	}
}
