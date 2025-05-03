package com.Nanda.Food_Delivery.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Nanda.Food_Delivery.Model.DeliveryAddress;

@Repository
public interface DeliveyAddressRepository extends JpaRepository<DeliveryAddress, Integer>
{
	List<DeliveryAddress> findAllByCustomerId(int customerId);
}
