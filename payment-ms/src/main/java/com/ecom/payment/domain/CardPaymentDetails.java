package com.ecom.payment.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardPaymentDetails {

	 public enum PaymentType {
		DEBIT_CARD, 
		CREDIT_CARD, 
		BANK
	}

	 public enum PaymentStatus {
		SUCCESS, 
		FAILED, 
		PENDING
	}

	
	private PaymentType paymentType;
	private PaymentStatus paymentStatus;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String startFromMonth;
	private String startFromyear;
	private String expiryMonth;
	private String expiryYear; 
	private String paymentauthToken;
	

}
