package com.Nanda.Food_Delivery.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "delivery_address")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class DeliveryAddress
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@NotEmpty
	String street;

	@NotEmpty
	String area;

	@NotEmpty
	String city;

	@ManyToOne
	@JoinColumn
	@ToString.Exclude
	Customer customer;
}