package com.Nanda.Food_Delivery.Model;

import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@EqualsAndHashCode(exclude = "restaurant")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder

public class Menu
{
	@Id
	int id;
    
    @OneToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    @ToString.Exclude
    Restaurant restaurant;

    @OneToMany(mappedBy = "menu",cascade = CascadeType.ALL)
    @Builder.Default
    @ToString.Exclude
    List<FoodItem> foodItemsList = new ArrayList<>();

}
