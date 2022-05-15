package com.ecom.order.vo;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class OrdersVO {


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


	List<OrderLinesVO> orderlines;

}
