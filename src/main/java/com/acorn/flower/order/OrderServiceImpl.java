package com.acorn.flower.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao dao;

	@Override
	public boolean insert(OrderDto dto) {
		int result = dao.insert(dto);

		if (result != 1)
			return false;

		return true;
	}

	@Override
	public OrderDto getOrder(int id) {
		OrderDto dto = dao.getOrder(id);
		return dto;
	}

	@Override
	public List<OrderDto> getOrders(int order_id) {
		List<OrderDto> list = dao.getOrders(order_id);
		return list;
	}

	@Override
	public List<OrderDto> getAllOrders() {
		List<OrderDto> list = dao.getAllOrders();
		return list;
	}

	@Override
	public boolean deleteAll(int order_id) {
		int result = dao.deleteAll(order_id);

		if (result == 0)
			return false;

		return true;
	}

	@Override
	public boolean delete(int id) {
		int result = dao.delete(id);

		if (result == 0)
			return false;

		return true;
	}

	@Override
	public boolean update(OrderDto dto) {
		int result = dao.update(dto);

		if (result == 0)
			return false;

		return true;
	}
}
