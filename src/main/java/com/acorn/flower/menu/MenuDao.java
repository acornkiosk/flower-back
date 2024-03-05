package com.acorn.flower.menu;

import java.util.List;

public interface MenuDao {
	public int insert(MenuDto dto);
	public List<MenuDto> getList(MenuDto dto);
	public MenuDto getMenu(MenuDto dto);
	public int update(MenuDto dto);
	public int delete(MenuDto dto);
	public int getCount(MenuDto dto);
	
}
