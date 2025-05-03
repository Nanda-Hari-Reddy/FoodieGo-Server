package com.Nanda.Food_Delivery.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Nanda.Food_Delivery.Model.FoodItem;

@Repository
public interface FoodItemsRepository extends JpaRepository<FoodItem, Integer>
{
	List<FoodItem> findByMenuId(int menuId);
}
