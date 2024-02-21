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
	 * 주문 추가
	 * @param dto
	 * @return
	 */
	@PostMapping("/api/order")
	public ResponseEntity<OrderResponse> addOrder(@RequestBody OrderDto dto) {
		boolean isSuccess;
		OrderResponse response = new OrderResponse();
		try {
			isSuccess = service.insert(dto);
			List<OrderDto> list = service.getOrders(dto.getOrder_id());
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

	/**
	 * 단일 주문 조회
	 * @param id
	 * @return
	 */
	@PostMapping("/api/order/get")
	public ResponseEntity<OrderResponse> getOrder(@RequestBody int id) {
		OrderResponse response = new OrderResponse();

		try {
			OrderDto dto = service.getOrder(id);
			if (dto != null) {
				log.info("order = {}", dto.toString());
				response.setDto(dto);
				response.setStatus(HttpStatus.OK);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				log.error(id + "번 주문은 없습니다");
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
	 * 주문번호 주문 리스트 조회
	 * @param order_id
	 * @return
	 */
	@PostMapping("/api/order/list")
	public ResponseEntity<OrderResponse> getOrderList(@RequestBody int order_id) {

		OrderResponse response = new OrderResponse();
		try {
			List<OrderDto> list = service.getOrders(order_id);
			if (!list.isEmpty()) {
				for (OrderDto dto : list) {
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

	/**
	 * 전체 주문 리스트 조회
	 * @return
	 */
	@GetMapping("/api/order/list")
	public ResponseEntity<OrderResponse> getAllOrderList() {

		OrderResponse response = new OrderResponse();
		try {
			List<OrderDto> list = service.getAllOrders();
			if (!list.isEmpty()) {
				for (OrderDto dto : list) {
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

	/**
	 * 전체 주문 삭제 
	 * @param order_id
	 * @return
	 */
	@PostMapping("/api/order/deleteAll")
	public ResponseEntity<OrderResponse> deleteAllOrders(@RequestBody int order_id) {
		boolean isSuccess;
		OrderResponse response = new OrderResponse();
		try {
			List<OrderDto> list = service.getOrders(order_id);
			isSuccess = service.deleteAll(order_id);
			if (isSuccess) {
				log.info("주문번호 " + order_id + "번이 삭제 되었습니다.");
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
	 * 단일 주문 삭제
	 * @param id
	 * @return
	 */
	@PostMapping("/api/order/delete")
	public ResponseEntity<OrderResponse> deleteOrders(@RequestBody int id) {
		boolean isSuccess;
		OrderResponse response = new OrderResponse();
		try {
			OrderDto dto = service.getOrder(id);
			isSuccess = service.delete(id);
			if (isSuccess) {
				log.info(id + "번이 삭제 되었습니다.");
				response.setDto(dto);
				response.setStatus(HttpStatus.OK);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				log.error("주문 삭제 실패");
				response.setDto(dto);
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
	 * 주문 정보 수정
	 * @param dto
	 * @return
	 */
	@PostMapping("/api/order/update")
	public ResponseEntity<OrderResponse> updateOrder(@RequestBody OrderDto dto) {
		boolean isSuccess;
		OrderResponse response = new OrderResponse();
		try {
			isSuccess = service.update(dto);
			OrderDto tmp = service.getOrder(dto.getId());
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
			log.error("서버에 문제가 있습니다.");
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
