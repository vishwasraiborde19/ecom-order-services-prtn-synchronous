package com.ecom.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.payment.domain.CardPaymentDetails;
import com.ecom.payment.service.PaymentService;

@RestController()
@RequestMapping("/bill")
public class PaymentController {
	
	@Autowired
	PaymentService paymentService;
	
	
	@GetMapping("/")
	public CardPaymentDetails getBill(String cartId) {
		return null;
		
	}

}
