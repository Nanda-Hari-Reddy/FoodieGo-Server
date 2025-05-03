package com.Nanda.Food_Delivery.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Nanda.Food_Delivery.Model.DeliveryPerson;

@Repository
public interface DeliveryPersonRepository extends JpaRepository<DeliveryPerson, Integer>
{
	@Query(value = "SELECT * FROM delivery_person ORDER BY RAND() LIMIT 1", nativeQuery = true)
	DeliveryPerson findRandomEntity();
}
