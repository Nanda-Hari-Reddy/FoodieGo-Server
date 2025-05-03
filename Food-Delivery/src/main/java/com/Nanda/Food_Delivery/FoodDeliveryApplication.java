package com.Nanda.Food_Delivery;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.activation.DataSource;

@SpringBootApplication
public class FoodDeliveryApplication 
{	
	
	public static void main(String[] args)
	{
		SpringApplication.run(FoodDeliveryApplication.class, args);
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer()
	{
		return new WebMvcConfigurer()
		{
			public void addCorsMappings(CorsRegistry registry)
			{
				registry.addMapping("/**")
		 		.allowedMethods("*")
				.allowedOrigins("http://localhost:3000");
			}
		};
	}
	
	@Bean
    public Class<?> CustomerControllerClass()
	{
        return Class.class;
    }
}
