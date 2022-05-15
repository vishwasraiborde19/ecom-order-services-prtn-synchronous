package com.ecom.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.payment.domain.CardPaymentDetails;
import com.ecom.payment.domain.CardPaymentDetails.PaymentStatus;
import com.ecom.payment.repository.PaymentRepo;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentRepo paymentRepo;
	
	
	
	public CardPaymentDetails makecardpayment (CardPaymentDetails cardPaymentDetails) {
		
		cardPaymentDetails.setPaymentStatus(PaymentStatus.SUCCESS);
		paymentRepo.save(cardPaymentDetails);
		return cardPaymentDetails;
		
	}

}
