package com.acorn.flower.order;

import java.util.List;

public interface OrderService {
	public boolean insert(OrderDto dto);

	public OrderDto getOrder(int id);

	public List<OrderDto> getOrders(int order_id);

	public List<OrderDto> getAllOrders();

	public boolean deleteAll(int order_id);

	public boolean delete(int id);

	public boolean update(OrderDto dto);
}
