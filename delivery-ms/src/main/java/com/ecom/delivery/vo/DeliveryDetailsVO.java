package com.ecom.delivery.vo;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDetailsVO {

	private String id;
	private String deliveryDate;
	private String deliveryTimeSlot;
	private String deliveryAddressLine1;
	private String deliveryAddressLine2;
	private String postCode;
}
