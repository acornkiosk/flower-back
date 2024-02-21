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

}
