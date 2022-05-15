package com.ecom.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ecom.order.service.OrderService;
import com.ecom.order.vo.CartVO;
import com.ecom.order.vo.OrdersVO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController()
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	OrderService orderService;

	@Autowired
	RestTemplate cartServiceClient;

	@PostMapping("/")
	public OrdersVO placeOrder(@RequestBody OrdersVO order) {

		// getCart Details
		//TODO externalise the urls and pull it via environment configuration 
		List<CartVO> cartDetails = cartServiceClient
		        .getForObject("http://CART-SERVICE/carts/" + order.getCartID() + "/cart", List.class);

		ObjectMapper mapper = new ObjectMapper();
		cartDetails = mapper.convertValue(cartDetails, new TypeReference<List<CartVO>>() {
		});

		System.out.println(cartDetails.toString());

		// get paymentDetails
		// List<CartVO> cartDetails = cartServiceClient.getForObject("CART-SERVICE",
		// List.class);

		// getDeliveryDetails
		// List<CartVO> cartDetails = cartServiceClient.getForObject("CART-SERVICE",
		// List.class);

		return orderService.placeOrder(order, cartDetails);
	}

}
