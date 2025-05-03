package com.Nanda.Food_Delivery.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Nanda.Food_Delivery.Model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>
{
	Optional<Customer> findByEmail(String email);
}
