package com.acorn.flower.order;

import java.util.List;

public interface OrderDao {
	public int insert(OrderDto dto);

	public OrderDto getOrder(OrderDto dto);

	public List<OrderDto> getOrders(OrderDto dto);

	public int delete(OrderDto dto);

	public int update(OrderDto dto);
}
