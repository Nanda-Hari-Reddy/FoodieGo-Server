package com.Nanda.Food_Delivery.exception;

public class MenuNotFoundException extends RuntimeException
{
	public MenuNotFoundException(String message)
	{
		super(message);
	}
}
