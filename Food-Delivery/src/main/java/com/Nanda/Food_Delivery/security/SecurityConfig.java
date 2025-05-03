package com.Nanda.Food_Delivery.security;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.Nanda.Food_Delivery.Model.Customer;
import com.Nanda.Food_Delivery.Model.RestaurantAdmin;
import com.Nanda.Food_Delivery.Repository.CustomerRepository;
import com.Nanda.Food_Delivery.Repository.RestaurantAdminRepository;
import com.Nanda.Food_Delivery.Repository.RestaurantRepository;
import com.Nanda.Food_Delivery.exception.RestaurantAdminNotFoundException;
import com.Nanda.Food_Delivery.exception.UserNotFoundException;

@Configuration
@EnableWebSecurity
public class SecurityConfig
{
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	RestaurantAdminRepository restaurantAdminRepository;
	
	@Bean
	public SecurityFilterChain configuresecurityfilters(HttpSecurity http) throws Exception
	{
		return http
		.authorizeHttpRequests(
			auth -> 
				auth
				.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				.requestMatchers( "/**").permitAll()
				.requestMatchers(HttpMethod.POST, "/user").permitAll()
				.requestMatchers(HttpMethod.GET, "/authenticate").permitAll()
				.requestMatchers(HttpMethod.POST, "/restaurants").permitAll()
				.requestMatchers(HttpMethod.HEAD, "/restaurants/resaurantadmin").permitAll()
				.requestMatchers("/swagger-ui/**").permitAll()
                .requestMatchers("/v3/api-docs/**").permitAll()
				.anyRequest().authenticated()
			)
		.httpBasic(Customizer.withDefaults())
		.sessionManagement(
			session -> session.sessionCreationPolicy
			(SessionCreationPolicy.STATELESS))
		.csrf(csrf -> csrf.disable()) 
		.build();
	}
	
	@Bean
	public UserDetailsService userDetaisService()
	{
		return username -> {
			if(customerOrAdmin(username))
			{
				System.out.println("It is Customer");
				Customer customer = customerRepository.findByEmail(username)
						.orElseThrow(() -> new UsernameNotFoundException("User not found with "+ username));
				return User.withUsername(customer.getEmail())
	                    .password(customer.getPassword())
	                    .build();
			}
            return null;
        };
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	private boolean customerOrAdmin(String username)
	{
		String regex = "^.+@gmail\\.com$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(username);
		boolean isCustomer = matcher.matches();

		if (isCustomer) return true;
		else return false;
	}
}
