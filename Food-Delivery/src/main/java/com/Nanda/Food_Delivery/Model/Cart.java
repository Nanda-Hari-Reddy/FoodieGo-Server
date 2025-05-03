package com.Nanda.Food_Delivery.Model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
public class Cart
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@Column(scale = 2)
	double cartTotal;

    @OneToOne
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
    @ToString.Exclude
	Customer customer;

	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
	@Builder.Default
	@ToString.Exclude
	List<Order_Entity> orders = new ArrayList<>();

}
