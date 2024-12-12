package com.order.service;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.entity.OrderEntity;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Service
public class OrderServiceImpl {

	private static final Log LOG = LogFactory.getLog(OrderServiceImpl.class);

	public Map<String, Object> createOrders(OrderEntity orderEntityDetails) {

		Map<String, Object> response = new HashMap<>();

		try {

			if (orderEntityDetails != null) {

				HttpHeaders httpHeaders = new HttpHeaders();
				httpHeaders.add("content-type", "application/json");

				JSONObject body = new JSONObject(orderEntityDetails);

				String productCatalogURLEndpoint = "";

				HttpEntity<?> entity = new HttpEntity<>(body.toString(), httpHeaders);
				RestTemplate restTemplate = new RestTemplate();
				ResponseEntity<?> productCatalogAPIResponse = restTemplate.postForEntity(productCatalogURLEndpoint,
						entity, HashMap.class);

				response = getMapByJson(productCatalogAPIResponse.getBody());

			}

		} catch (Exception e) {
			LOG.error("ERROR @createOrders =>" + e.getMessage(), e);
		}
		return response;
	}

	public Map<String, Object> getUserOrders(String mobileNumber) {

		Map<String, Object> response = new HashMap<>();
		response.put("status", "ERROR");
		response.put("message", "Oops! something went wrong.Try again later");

		try {

			if (!mobileNumber.isEmpty()) {

				HttpHeaders httpHeaders = new HttpHeaders();
				httpHeaders.add("content-type", "application/json");

				HashMap<String, Object> requestBody = new HashMap<>();
				requestBody.put("MOBILE_NUMBER", mobileNumber);

				JSONObject body = new JSONObject(requestBody);

				String productCatalogURLEndpoint = "";

				HttpEntity<?> entity = new HttpEntity<>(body.toString(), httpHeaders);
				RestTemplate restTemplate = new RestTemplate();
				ResponseEntity<?> productCatalogAPIResponse = restTemplate.postForEntity(productCatalogURLEndpoint,
						entity, HashMap.class);

				response = getMapByJson(productCatalogAPIResponse.getBody());

			}

		} catch (Exception e) {
			LOG.error("ERROR @getUserOrders =>" + e.getMessage(), e);

		}
		return response;
	}

	public static Map<String, Object> getMapByJson(Object json) {
		HashMap<String, Object> map = new HashMap<>();

		try {

			ObjectMapper mapper = new ObjectMapper();
			map = mapper.readValue(String.valueOf(json), new TypeReference<HashMap<String, Object>>() {
			});

		} catch (Exception e) {

		}
		return map;
	}

}
