package com.ecom.order.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.order.domain.OrderLines;
import com.ecom.order.domain.Orders;
import com.ecom.order.repository.OrderLineRepository;
import com.ecom.order.repository.OrderRepository;
import com.ecom.order.vo.CartVO;
import com.ecom.order.vo.OrderLinesVO;
import com.ecom.order.vo.OrdersVO;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	OrderLineRepository orderLineRepository;
	
	

	public OrdersVO placeOrder(OrdersVO orderVo, List<CartVO> cartDetails) {

		if (orderVo.getOrderNumber() == null) {
			Orders order = new Orders();
			BeanUtils.copyProperties(orderVo, order);
			orderRepository.save(order);

			// create orderlines from cart details
			// this code can be parallel processed and passed to a topic and processed
			List<OrderLines> orderlines = new ArrayList<>();
			cartDetails.stream().forEach(cart ->
			{
				OrderLines orderline = new OrderLines();
				orderline.setProductId(cart.getProductId());
				orderline.setPrice(200d);
				orderline.setQty(cart.getQty());
				orderline.setOrderNumber(order.getOrderNumber());
				orderlines.add(orderline);
			});
			orderLineRepository.saveAll(orderlines);
			
			
			// return the persisted state
			Orders persistedOrder = orderRepository.getById(order.getOrderNumber());
			OrdersVO persistedOrderVO = new OrdersVO();
			BeanUtils.copyProperties(persistedOrder, persistedOrderVO);
			List<OrderLinesVO> orderLines = getOrderlinesforOrderNumber(order.getOrderNumber());
			persistedOrderVO.setOrderlines(orderLines);
			
			return persistedOrderVO;

		} else {
			// this is bad 
			return new OrdersVO();
		}

	}
	
	private List<OrderLinesVO> getOrderlinesforOrderNumber(Integer orderNumber) {
		List<OrderLines> orderLines = orderLineRepository.getOrderlinesforOrderNumber(orderNumber);
		List<OrderLinesVO> orderLinesVO = new ArrayList<>();
		
		orderLines.stream().forEach( line -> {
			OrderLinesVO orderlineVO = new OrderLinesVO();
			BeanUtils.copyProperties(line, orderlineVO);
			orderLinesVO.add(orderlineVO);
		});
		return orderLinesVO;
	}

	public Orders cancelOrder(Orders order) {

		orderRepository.delete(order);
		return order;
	}

}
