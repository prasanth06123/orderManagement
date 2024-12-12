package com.order.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.order.service.OrderServiceImpl;
import com.product.entity.OrderEntity;

@RestController
public class OrdersApiController implements OrdersApi {

	OrderServiceImpl orderServiceImpl = new OrderServiceImpl();

	@Override
	public ResponseEntity<Object> createOrders(OrderEntity orderEntityDetails) {

		Map<String, Object> response = new HashMap<>();
		if (orderEntityDetails != null) {

			response = orderServiceImpl.createOrders(orderEntityDetails);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		return null;
	}

	@Override
	public ResponseEntity<Object> getUserOrders(String mobileNumber) {

		Map<String, Object> response = new HashMap<>();
		if (mobileNumber != null) {

			response = orderServiceImpl.getUserOrders(mobileNumber);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		return null;

	}

}
