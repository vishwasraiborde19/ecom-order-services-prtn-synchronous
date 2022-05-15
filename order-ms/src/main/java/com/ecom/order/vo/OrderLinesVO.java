package com.ecom.order.vo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderLinesVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderlineId;
	private Integer orderNumber;

	Integer productId;
	Integer qty;
	Double price;
}
