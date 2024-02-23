package com.acorn.flower.common;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommonDaoImpl implements CommonDao {
	@Autowired
	private SqlSession session;
	
	@Override
	public List<CommonDto> getChild(int code_id) {
		List<CommonDto> list = session.selectList("common.getChild",code_id);
		return list;
	}

	@Override
	public int insert(CommonDto dto) {
		System.out.println(dto);
		int result = session.insert("common.insert", dto);
		return result;
		
	}

	@Override
	public int update(CommonDto dto) {
		int result = session.update("common.update", dto);
		return result;
	}

	@Override
	public int delete(int code_id) {
		int result = session.delete("common.delete", code_id);
		return result;
	}

	@Override
	public CommonDto getCommon(int code_id) {
		CommonDto dto = session.selectOne("common.getCommon", code_id);
		return dto;
	}
}
