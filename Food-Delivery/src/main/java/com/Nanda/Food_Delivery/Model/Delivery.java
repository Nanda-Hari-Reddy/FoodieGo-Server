package com.Nanda.Food_Delivery.Model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Delivery
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int delivery_id;
	
	@OneToOne
    @JoinColumn
    @ToString.Exclude
	Order_Entity order;
	
//	@CreationTimestamp
//    Date orderTime;
	
	@CreationTimestamp
	@Column(name = "created_time")
    LocalDateTime createdAt;
	
	@Column(scale = 2)
	double cost;

	String customerName;
	
	String customerEmail;
	
	String restaurantName;
	
	@Embedded
	@ToString.Exclude
	DeliveryAddressEmbedded addressEmbedded;
	
	String deliveryTime;

	@ManyToOne
	@JoinColumn
	@ToString.Exclude
	DeliveryPerson deliveryPerson;
}
