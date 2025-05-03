package com.Nanda.Food_Delivery.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Nanda.Food_Delivery.Model.Delivery;
import com.Nanda.Food_Delivery.Model.Order_Entity;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Integer>
{
	List<Delivery> findByDeliveryPersonId(int deliveryPersonId);
	List<Delivery> findByCustomerEmail(String customerEmail);
	Optional<Delivery> findByOrder(Order_Entity order);
}
