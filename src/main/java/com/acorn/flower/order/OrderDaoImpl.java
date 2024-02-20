package com.acorn.flower.order;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl implements OrderDao{

	@Autowired
	private SqlSession session;
	
	@Override
	public int insert(OrderDto dto) {
		int result = session.insert("order.insert",dto);
		return result;
	}

	@Override
	public List<OrderDto> getOrders(int id) {
		List<OrderDto> list = session.selectList("order.getOrders",id);
		return list;
	}

	@Override
	public int deleteAll(int id) {
		int result = session.delete("order.deleteAll",id);
		return result;
	}

}
