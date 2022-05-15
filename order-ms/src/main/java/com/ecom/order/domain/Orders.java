package com.ecom.order.domain;

import java.util.Date;

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
@Entity(name="Orders")
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderNumber;

	private Date orderDate;
	private Date requiredDate;
	private Date shippedDate;
	private String status;
	private String comments;

	private Integer customerID;
	private Integer cartID;
	private Integer deliveryID;
	private Integer paymentID;

//	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//	List<OrderLines> orderlines = new ArrayList<>();

}
