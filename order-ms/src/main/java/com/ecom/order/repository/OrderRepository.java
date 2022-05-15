package com.ecom.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.order.domain.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer>{

}
