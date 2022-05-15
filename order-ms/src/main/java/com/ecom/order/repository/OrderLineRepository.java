package com.ecom.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecom.order.domain.OrderLines;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLines, Integer>{

	@Query(value="select * from orderlines where ordernumber = ?", nativeQuery = true)
	List<OrderLines> getOrderlinesforOrderNumber(Integer orderNumber);

}
