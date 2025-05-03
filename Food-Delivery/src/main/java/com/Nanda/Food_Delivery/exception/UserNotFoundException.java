package com.Nanda.Food_Delivery.exception;

public class UserNotFoundException extends RuntimeException
{
	public UserNotFoundException(String message)
	{
		super(message);
	}
}
