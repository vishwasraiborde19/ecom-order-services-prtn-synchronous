package com.ecom.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.payment.domain.DeliveryDetails;

@Repository
public interface DeliveryRepo extends JpaRepository<DeliveryDetails, Long>{

}