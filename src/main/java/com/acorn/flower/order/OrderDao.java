package com.acorn.flower.order;

import java.util.List;

public interface OrderDao {
	public int insert(OrderDto dto);

	public OrderDto getOrder(int id);

	public List<OrderDto> getOrders(int order_id);

	public List<OrderDto> getAllOrders();

	public int deleteAll(int order_id);

	public int delete(int id);

	public int update(OrderDto dto);
}
