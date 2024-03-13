package com.acorn.flower.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acorn.flower.common.CommonDao;
import com.acorn.flower.common.CommonDto;
import com.acorn.flower.common.CommonService;
import com.acorn.flower.common.CommonServiceImpl;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao dao;
	@Autowired
	private CommonService commonService;

	@Override
	public boolean insert(OrderDto dto) {
		int result = dao.insert(dto);

		if (result != 1)
			return false;

		return true;
	}

	@Override
	public OrderDto getOrder(OrderDto dto) {
		OrderDto result = dao.getOrder(dto);
		return result;
	}

	@Override
	public List<OrderDto> getOrders(OrderDto dto) {
		List<OrderDto> list = dao.getOrders(dto);
		for(OrderDto result : list) {
			String[] code_ids = result.getOptions().split(", ");
			StringBuilder codeNamesBuilder = new StringBuilder();
			for(String codeIdString : code_ids) {
				int code_id=Integer.parseInt(codeIdString.trim()); 
				CommonDto commonDto = new CommonDto();
				commonDto.setCode_id(code_id);
				String code_name = commonService.getCommon(commonDto).getCode_name();
				codeNamesBuilder.append(code_name).append(", ");
			}
			String code_names = codeNamesBuilder.toString();
			if(code_names.length() > 0) {
				code_names = code_names.substring(0, code_names.length() -2);
			}
			result.setOptions_name(code_names);
		}
		return list;
	}

	@Override
	public boolean deleteAll(OrderDto dto) {
		int result = dao.deleteAll(dto);

		if (result == 0)
			return false;

		return true;
	}

	@Override
	public boolean delete(OrderDto dto) {
		int result = dao.delete(dto);

		if (result == 0)
			return false;

		return true;
	}

	@Override
	public boolean update(OrderDto dto) {
		int result = dao.update(dto);

		if (result == 0)
			return false;

		return true;
	}

	@Override
	public int getCartId() {
		int cartId = dao.getCartId();
		return cartId;
	}
}
