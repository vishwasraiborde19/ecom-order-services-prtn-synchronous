package com.ecom.order.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Orderlines")
public class OrderLines {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderlineId;
	private Integer orderNumber;

	Integer productId;
	Integer qty;
	Double price;
}
