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
	public MenuDto getMenu(int id) {
		MenuDto dto = session.selectOne("menu.getMenu", id);
		return dto;
	}

	@Override
	public int update(MenuDto dto) {
		int result=session.update("menu.update",dto);
		return result;
	}

	@Override
	public int delete(int id) {
		int result= session.delete("menu.delete",id);
		return result;
	}

	@Override
	public MenuDto getLast() {
		MenuDto dto=session.selectOne("menu.getLast");
		return dto;
	}
}
