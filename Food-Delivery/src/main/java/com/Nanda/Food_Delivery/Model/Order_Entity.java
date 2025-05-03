package com.Nanda.Food_Delivery.Model;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
public class Order_Entity
{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@ManyToOne
	@JoinColumn
	@ToString.Exclude
	FoodItem foodItem;

	int quantity;

	@Column(scale = 2)
	double totalCost;

	@ManyToOne
	@JoinColumn
	@ToString.Exclude
	Restaurant restaurant;

	@ManyToOne
	@JoinColumn
	@ToString.Exclude
	Cart cart;

	@ManyToOne
	@JoinColumn
	@ToString.Exclude
    Customer customer;
	
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	@ToString.Exclude
	Delivery delivery;
}

//class Order_EntityCompare
