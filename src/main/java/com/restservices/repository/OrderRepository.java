package com.restservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restservices.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
	
}
