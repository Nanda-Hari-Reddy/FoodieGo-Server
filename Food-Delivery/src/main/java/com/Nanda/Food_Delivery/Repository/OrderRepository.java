package com.Nanda.Food_Delivery.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Nanda.Food_Delivery.Model.Customer;
import com.Nanda.Food_Delivery.Model.FoodItem;
import com.Nanda.Food_Delivery.Model.Order_Entity;
import com.Nanda.Food_Delivery.Model.Restaurant;

@Repository
public interface OrderRepository extends JpaRepository<Order_Entity, Integer>
{
	Optional<Order_Entity> findByFoodItem(FoodItem foodItem);
	List<Order_Entity> findByRestaurant(Restaurant restaurant);
	List<Order_Entity> findByCustomer(Customer customer);
}
