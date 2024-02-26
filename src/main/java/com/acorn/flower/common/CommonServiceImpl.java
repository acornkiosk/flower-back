package com.acorn.flower.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonServiceImpl implements CommonService {
	@Autowired
	private CommonDao dao;

	@Override
	public List<CommonDto> getChild(CommonDto dto) {
		List<CommonDto> list = dao.getChild(dto);
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
	public boolean deleteRow(CommonDto dto) {
		int result = dao.delete(dto);
		
		if(result != 1) return false;
		
		return true;
		
	}

	@Override
	public CommonDto getCommon(CommonDto dto) {
		CommonDto dto2 = dao.getCommon(dto);
		
		return dto2;
	}
}
