package com.Nanda.Food_Delivery.Model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


import com.Nanda.Food_Delivery.enums.Opened;
import com.Nanda.Food_Delivery.enums.RestarauntCategory;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@EqualsAndHashCode(exclude = "menu")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Restaurant
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@Column(name = "picture")
	String imageURL;

	@NotBlank(message = "Hotel Name Must not be empty")
	@Column(unique = true)
	String restaurantName;
	
	@NotBlank(message = "Location Must not be empty")
	String location;

	@Enumerated(EnumType.STRING)
	RestarauntCategory restaurantCategory;

	@Size(min = 10, max = 10 ,message = "contact number must be 10 numbers")
	String contactNumber;

	@Enumerated(EnumType.STRING)
	@Builder.Default
	Opened opened = Opened.OPENED;
	
	@OneToOne(mappedBy = "restaurant", cascade = CascadeType.ALL)
	@ToString.Exclude
    Menu menu;
	
	LocalTime opens;
	
	LocalTime closes;
	
	@OneToOne(mappedBy = "restaurant", cascade = CascadeType.ALL)
	@ToString.Exclude
	RestaurantAdmin admin;

	@Builder.Default
	@JsonIgnore
	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
	@ToString.Exclude
	List<Order_Entity> order_entity = new ArrayList<>();
}
