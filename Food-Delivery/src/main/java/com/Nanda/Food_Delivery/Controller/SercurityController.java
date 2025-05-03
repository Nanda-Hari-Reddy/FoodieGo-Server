package com.Nanda.Food_Delivery.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;



@RestController
public class SercurityController 
{
	@GetMapping(path = "/authenticate")
	public String authenticate()
	{
		return "authenticated";
	}	
}
