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

	@Override
	public boolean insertRow(CommonDto dto) {
		System.out.println(dto);
		int result = dao.insert(dto);
		
		if(result != 1) return false;
		
		return true;
		
	}

	@Override
	public boolean updateRow(CommonDto dto) {
		int result = dao.update(dto);
		
		if(result == 0) return false;
		
		return true;
		
	}

	@Override
	public boolean deleteRow(int code_id) {
		int result = dao.delete(code_id);
		
		if(result != 1) return false;
		
		return true;
		
	}

	@Override
	public CommonDto getCommon(int code_id) {
		CommonDto dto = dao.getCommon(code_id);
		
		return dto;
	}
}
