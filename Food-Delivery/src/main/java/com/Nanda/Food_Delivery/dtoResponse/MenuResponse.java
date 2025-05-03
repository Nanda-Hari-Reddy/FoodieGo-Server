package com.Nanda.Food_Delivery.dtoResponse;

import java.util.ArrayList;
import java.util.List;

import com.Nanda.Food_Delivery.Model.Restaurant;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuResponse
{
    
	int id;

    @JsonIgnore
    @ToString.Exclude
    Restaurant restaurant;

    @Builder.Default
    @ToString.Exclude
    List<FoodResponse> foodItemsList = new ArrayList<>();
}
