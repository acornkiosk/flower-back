package com.acorn.flower.order;

import java.util.List;

public interface OrderService {
	public boolean insert(OrderDto dto);

	public OrderDto getOrder(OrderDto dto);

	public List<OrderDto> getOrders(OrderDto dto);

	public boolean deleteAll(OrderDto dto);

	public boolean delete(OrderDto dto);

	public boolean update(OrderDto dto);
}
