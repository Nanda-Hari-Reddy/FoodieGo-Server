package com.Nanda.Food_Delivery.Model;

import java.util.ArrayList;
import java.util.List;

import com.Nanda.Food_Delivery.enums.FoodCategory;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class FoodItem
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@Column(name = "picture")
	String imageURL;
	
	@NotEmpty
	String dishName;

	@Column(scale = 2)
	double price;

	@Enumerated(EnumType.STRING)
	FoodCategory foodCategory;

	@ManyToOne
	@JoinColumn
	@ToString.Exclude
	@JsonIgnore
	Menu menu;
	
	@JsonIgnore
	@Builder.Default
	@OneToMany(mappedBy = "foodItem", cascade = CascadeType.ALL)
	@ToString.Exclude
	List<Order_Entity> orders = new ArrayList<>();
}
