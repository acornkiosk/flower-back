package com.acorn.flower.order;

import java.util.List;

public interface OrderService {
	public boolean insert(OrderDto dto);

	public List<OrderDto> getOrders(int id);

	public boolean deleteAll(int id);
}
