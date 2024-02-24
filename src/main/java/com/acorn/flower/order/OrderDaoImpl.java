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
	public OrderDto getOrder(OrderDto dto) {
		OrderDto result = session.selectOne("order.getOrder", dto);
		return result;
	}

	@Override
	public List<OrderDto> getOrders(OrderDto dto) {
		List<OrderDto> list = session.selectList("order.getOrders", dto);
		return list;
	}

	@Override
	public int delete(OrderDto dto) {
		int result = session.delete("order.delete", dto);
		return result;
	}

	@Override
	public int update(OrderDto dto) {
		int result = session.update("order.update", dto);
		return result;
	}

}
