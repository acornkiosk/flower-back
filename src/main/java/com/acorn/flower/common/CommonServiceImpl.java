package com.acorn.flower.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonServiceImpl implements CommonService {
	@Autowired
	private CommonDao dao;

	@Override
	public List<CommonDto> getChild(int code_id) {
		List<CommonDto> list = dao.getChild(code_id);
		return list;
	}
}
