package com.Nanda.Food_Delivery.dtoResponse;

import java.util.ArrayList;
import java.util.List;

import com.Nanda.Food_Delivery.enums.Gender;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class DeliveryPersonResponse
{
	int id;

	@NotEmpty
	String name;

	@Size(min = 10, max = 10)
	String phoneNo;

	@Enumerated(EnumType.STRING)
	Gender gender;

	String email;

	@Builder.Default
	List<DeliveryResponse> ordersToCarry = new ArrayList<>();
}
