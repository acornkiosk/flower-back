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
	public OrderDto getOrder(OrderDto dto) {
		OrderDto result = dao.getOrder(dto);
		return result;
	}

	@Override
	public List<OrderDto> getOrders(OrderDto dto) {
		List<OrderDto> list = dao.getOrders(dto);
		return list;
	}

	@Override
	public boolean deleteAll(OrderDto dto) {
		int result = dao.deleteAll(dto);

		if (result == 0)
			return false;

		return true;
	}

	@Override
	public boolean delete(OrderDto dto) {
		int result = dao.delete(dto);

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
