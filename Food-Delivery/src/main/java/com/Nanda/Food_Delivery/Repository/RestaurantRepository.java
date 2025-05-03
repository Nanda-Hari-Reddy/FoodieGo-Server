package com.Nanda.Food_Delivery.Repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Nanda.Food_Delivery.Model.Restaurant;
import com.Nanda.Food_Delivery.Model.RestaurantAdmin;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>
{
	Optional<Restaurant> findByAdmin(RestaurantAdmin admin);
}
