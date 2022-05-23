package com.ecom.delivery.domain;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor

@Document(collection = "delivery")
public class DeliveryDetails {

	@Id
	private String id;
	private String deliveryDate;
	private String deliveryTimeSlot;
	private String deliveryAddressLine1;
	private String deliveryAddressLine2;
	private String postCode;
}
