package com.Nanda.Food_Delivery.Model;

import java.util.ArrayList;
import java.util.List;

import com.Nanda.Food_Delivery.enums.Gender;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
public class DeliveryPerson
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@NotEmpty
	String name;

	@Size(min = 10, max = 10)
	String phoneNo;

	@Enumerated(EnumType.STRING)
	Gender gender;

	@Min(18) @Max(50)
	int age;

	@Email
    @Column(unique = true)
	String email;

	@Size(min = 8)
	String password;

	@OneToMany(mappedBy = "deliveryPerson",cascade = CascadeType.ALL)
	@Builder.Default
	@ToString.Exclude
	List<Delivery> deliveries = new ArrayList<>();
}
