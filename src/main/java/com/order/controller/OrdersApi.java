package com.order.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.product.entity.OrderEntity;

public interface OrdersApi {

	@PostMapping("/api/createOrders")
	ResponseEntity<Object> createOrders(@RequestBody OrderEntity orderEntityDetails);

	@PostMapping("/api/getUserOrders")
	ResponseEntity<Object> getUserOrders(@RequestBody String mobileNumber);

}
