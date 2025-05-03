package com.Nanda.Food_Delivery.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Nanda.Food_Delivery.Model.RestaurantAdmin;

@Repository
public interface RestaurantAdminRepository extends JpaRepository<RestaurantAdmin, Integer>
{
	Optional<RestaurantAdmin> findByUserName(String userName);
}
