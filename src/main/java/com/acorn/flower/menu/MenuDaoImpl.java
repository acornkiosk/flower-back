package com.acorn.flower.menu;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MenuDaoImpl implements MenuDao {
	@Autowired
	SqlSession session;

	@Override
	public int insert(MenuDto dto) {
		int result = session.insert("menu.insert", dto);
		return result;
	}

	@Override
	public List<MenuDto> getList(MenuDto dto) {
		List<MenuDto> list = session.selectList("menu.getList",dto);
		return list;
	}

	@Override
	public MenuDto getMenu(MenuDto dto) {
		MenuDto result = session.selectOne("menu.getMenu", dto);
		return result;
	}

	@Override
	public int update(MenuDto dto) {
		int result=session.update("menu.update",dto);
		return result;
	}

	@Override
	public int delete(MenuDto dto) {
		int result= session.delete("menu.delete",dto);
		return result;
	}

	@Override
	public int getCount(MenuDto dto) {
		int result=session.selectOne("menu.getCount",dto);
		return result;
	}

	
}
