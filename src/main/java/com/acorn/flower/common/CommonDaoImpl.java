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
	public List<CommonDto> getChild(CommonDto dto) {
		List<CommonDto> list = session.selectList("common.getChild",dto);
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
	public int delete(CommonDto dto) {
		int result = session.delete("common.delete", dto);
		return result;
	}

	@Override
	public CommonDto getCommon(CommonDto dto) {
		CommonDto dto2 = session.selectOne("common.getCommon", dto);
		return dto2;
	}
}
