package com.acorn.flower.menu;

import java.util.List;

public interface MenuService {
	public boolean insert(MenuDto dto);
	public List<MenuDto> getList(MenuDto dto); 
	public MenuDto getMenu(MenuDto dto);
	public boolean update(MenuDto dto);
	public boolean delete(MenuDto dto);
	
}
