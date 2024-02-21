package com.acorn.flower.menu;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MenuDaoImpl implements MenuDao {
	@Autowired
	SqlSession session;

	@Override
	public int insert(MenuDto dto) {
		int result=session.insert("menu.insert",dto);
		return result;
	}
}
