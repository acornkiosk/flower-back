package com.acorn.flower.order;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class OrderController {

	@Autowired
	private OrderService service;
	
	@PostMapping("/api/order")
	public ResponseEntity<OrderResponse> addOrder(@RequestBody OrderDto dto) {
		boolean isSuccess;
		OrderResponse response = new OrderResponse();
		try {
			isSuccess = service.insert(dto);
			List<OrderDto> list = service.getOrders(dto.getId());
			if (isSuccess) {
				log.info("order = {}", dto.toString());
				response.setList(list);
				response.setStatus(HttpStatus.OK);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				log.error("주문 추가 실패");
				response.setList(list);
				response.setStatus(HttpStatus.BAD_REQUEST);
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			log.error("서버에 문제가 있습니다.");
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/api/order/list")
	public ResponseEntity<OrderResponse> getOrderList(@RequestBody int id) {
		
		OrderResponse response = new OrderResponse();
		try {
			List<OrderDto> list = service.getOrders(id);
			if (!list.isEmpty()) {
				for(OrderDto dto : list) {
					log.info("order = {}", dto.toString());
				}
				response.setList(list);
				response.setStatus(HttpStatus.OK);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				log.error("주문 조회 실패");
				response.setList(list);
				response.setStatus(HttpStatus.BAD_REQUEST);
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			log.error("서버에 문제가 있습니다.");
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/api/order/deleteAll")
	public ResponseEntity<OrderResponse> deleteAllOrders(@RequestBody int id) {
		boolean isSuccess;
		OrderResponse response = new OrderResponse();
		try {
			List<OrderDto> list = service.getOrders(id);
			isSuccess = service.deleteAll(id);
			if (isSuccess) {
				log.info("주문번호 " + id + "번이 삭제 되었습니다.");
				response.setList(list);
				response.setStatus(HttpStatus.OK);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				log.error("주문 삭제 실패");
				response.setList(list);
				response.setStatus(HttpStatus.BAD_REQUEST);
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			log.error("서버에 문제가 있습니다.");
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
