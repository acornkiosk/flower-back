package com.acorn.flower.order;

import java.util.List;

public interface OrderDao {
	public int insert(OrderDto dto);

	public List<OrderDto> getOrders(int id);

	public int deleteAll(int id);
}
