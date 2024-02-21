package com.acorn.flower.order;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl implements OrderDao {

	@Autowired
	private SqlSession session;

	@Override
	public int insert(OrderDto dto) {
		int result = session.insert("order.insert", dto);
		return result;
	}

	@Override
	public OrderDto getOrder(int id) {
		OrderDto dto = session.selectOne("order.getOrder", id);
		return dto;
	}

	@Override
	public List<OrderDto> getOrders(int order_id) {
		List<OrderDto> list = session.selectList("order.getOrders", order_id);
		return list;
	}

	@Override
	public List<OrderDto> getAllOrders() {
		List<OrderDto> list = session.selectList("order.getAllOrders");
		return list;
	}

	@Override
	public int deleteAll(int order_id) {
		int result = session.delete("order.deleteAll", order_id);
		return result;
	}

	@Override
	public int delete(int id) {
		int result = session.delete("order.delete", id);
		return result;
	}

	@Override
	public int update(OrderDto dto) {
		int result = session.update("order.update", dto);
		return result;
	}

}
