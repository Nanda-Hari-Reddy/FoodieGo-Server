package com.Nanda.Food_Delivery.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Nanda.Food_Delivery.Model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>
{
	Optional<Cart> findByCustomerId(int customerId);
}
