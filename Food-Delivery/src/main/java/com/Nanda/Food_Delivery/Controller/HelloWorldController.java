package com.Nanda.Food_Delivery.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Nanda.Food_Delivery.dtoRequests.Nanda;

import io.swagger.v3.oas.annotations.parameters.RequestBody;


@RestController
@RequestMapping("/hello")
public class HelloWorldController
{
	@GetMapping(path = "{name}")
	public String sayHello(@PathVariable String name)
	{
		return "Hello "+name;
	}
	@PostMapping
	public void postMethodName(@RequestBody Nanda nanda)
	{
		System.out.println(nanda.getName());
	}


}
