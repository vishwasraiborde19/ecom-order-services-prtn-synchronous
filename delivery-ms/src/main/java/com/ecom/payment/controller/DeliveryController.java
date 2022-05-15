package com.ecom.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.payment.domain.DeliveryDetails;
import com.ecom.payment.service.DeliveryService;

@RestController()
@RequestMapping("/deliveries")
public class DeliveryController {
	
	@Autowired
	DeliveryService deliveryService;
	
	
	@PostMapping("/")
	public DeliveryDetails addDelivery(@RequestBody DeliveryDetails deliveryDetails) {
		return deliveryService.addDelivery(deliveryDetails);
	}

}
