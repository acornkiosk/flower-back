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

		if (result != 1) return false;

		return true;
	}

	@Override
	public List<OrderDto> getOrders(int id) {
		List<OrderDto> list = dao.getOrders(id);
		return list;
	}

	@Override
	public boolean deleteAll(int id) {
		int result = dao.deleteAll(id);
		
		if(result == 0) return false;
		
		return true;
	}

}
