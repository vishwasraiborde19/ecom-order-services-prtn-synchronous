package com.ecom.order.vo;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String sessionId;
	private Integer cartId;
	private Integer productId;
	private Integer qty;
	private String userStatus;
	private String cartStatus;
	private Date cartInceptionTime;

		
}
