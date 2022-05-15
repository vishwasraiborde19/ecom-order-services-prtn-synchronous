package com.ecom.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.payment.domain.DeliveryDetails;
import com.ecom.payment.repository.DeliveryRepo;

@Service
public class 	DeliveryService {
	
	@Autowired
	private DeliveryRepo deliveryRepo;
	
	
	
	public DeliveryDetails addDelivery (DeliveryDetails deliveryDetails) {
		
		
		return deliveryRepo.save(deliveryDetails);
		
	}

}
