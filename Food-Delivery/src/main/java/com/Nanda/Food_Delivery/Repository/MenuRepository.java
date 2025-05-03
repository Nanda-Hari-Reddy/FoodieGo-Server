package com.Nanda.Food_Delivery.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Nanda.Food_Delivery.Model.Menu;
import com.Nanda.Food_Delivery.Model.Restaurant;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer>
{
	Menu findMenuByRestaurant(Restaurant restaurant);
}
