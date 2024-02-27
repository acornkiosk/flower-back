package com.acorn.flower.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * 주문 관리
 */
@Slf4j
@RestController
public class OrderController {

	@Autowired
	private OrderService service;

	/**
	 * 주문 추가 body : order dto
	 * @param dto
	 * @return
	 */
	@PostMapping("/api/order")
	public ResponseEntity<OrderResponse> addOrder(@RequestBody OrderDto dto) {
		boolean isSuccess;
		OrderResponse response = new OrderResponse();
		try {
			isSuccess = service.insert(dto);
			List<OrderDto> list = service.getOrders(dto);
			if (isSuccess) {
				log.info("order = {}", dto.toString());
				response.setDto(dto);
				response.setStatus(HttpStatus.OK);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				log.error("주문 추가 실패");
				response.setStatus(HttpStatus.BAD_REQUEST);
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("서버에 문제가 있습니다.");
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 단일 주문 조회 body : order id
	 * @param id
	 * @return
	 */
	@PostMapping("/api/order/get")
	public ResponseEntity<OrderResponse> getOrder(@RequestBody OrderDto dto) {
		OrderResponse response = new OrderResponse();

		try {
			OrderDto result = service.getOrder(dto);
			if (result != null) {
				log.info("order = {}", dto.toString());
				response.setDto(result);
				response.setStatus(HttpStatus.OK);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				log.error(dto.getId() + "번 주문은 없습니다");
				response.setStatus(HttpStatus.BAD_REQUEST);
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			log.error("서버에 문제가 있습니다.");
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 주문번호 주문 리스트 조회 body order order_id = 0 이면 전체 조회
	 * @param order_id
	 * @return
	 */
	@PostMapping("/api/order/list")
	public ResponseEntity<OrderResponse> getOrderList(@RequestBody OrderDto dto) {

		OrderResponse response = new OrderResponse();
		try {
			List<OrderDto> list = service.getOrders(dto);
			if (!list.isEmpty()) {
				for (OrderDto item : list) {
					log.info("order = {}", item.toString());
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

	/**
	 * 전체 주문 삭제 body : order order_id
	 * @param order_id
	 * @return
	 */
	@PostMapping("/api/order/deleteAll")
	public ResponseEntity<OrderResponse> deleteAllOrders(@RequestBody OrderDto dto) {
		boolean isSuccess;
		OrderResponse response = new OrderResponse();
		try {
			List<OrderDto> list = service.getOrders(dto);
			isSuccess = service.deleteAll(dto);
			if (isSuccess) {
				log.info("주문번호 " + dto.getOrder_id() + "번이 삭제 되었습니다.");
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

	/**
	 * 단일 주문 삭제 body : order id
	 * @param id
	 * @return
	 */
	@PostMapping("/api/order/delete")
	public ResponseEntity<OrderResponse> deleteOrders(@RequestBody OrderDto dto) {
		boolean isSuccess;
		OrderResponse response = new OrderResponse();
		try {
			OrderDto result = service.getOrder(dto);
			isSuccess = service.delete(dto);
			if (isSuccess) {
				log.info(dto.getId() + "번이 삭제 되었습니다.");
				response.setDto(result);
				response.setStatus(HttpStatus.OK);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				log.error("주문 삭제 실패");
				response.setStatus(HttpStatus.BAD_REQUEST);
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			log.error("서버에 문제가 있습니다.");
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 주문 정보 수정 body order dto
	 * @param dto
	 * @return
	 */
	@PostMapping("/api/order/update")
	public ResponseEntity<OrderResponse> updateOrder(@RequestBody OrderDto dto) {
		boolean isSuccess;
		OrderResponse response = new OrderResponse();
		try {
			isSuccess = service.update(dto);
			OrderDto tmp = service.getOrder(dto);
			if (isSuccess) {
				log.info(dto.getId() + "번이 변경 되었습니다.");
				response.setDto(tmp);
				response.setStatus(HttpStatus.OK);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				log.error("주문 변경 실패");
				response.setDto(dto);
				response.setStatus(HttpStatus.BAD_REQUEST);
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("서버에 문제가 있습니다.");
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
