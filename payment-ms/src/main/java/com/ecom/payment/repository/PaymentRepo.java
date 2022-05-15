package com.ecom.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.payment.domain.CardPaymentDetails;

@Repository
public interface PaymentRepo extends JpaRepository<CardPaymentDetails, Long>{

}