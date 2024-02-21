package com.acorn.flower.menu;

import java.util.List;

public interface MenuService {
	public boolean insert(MenuDto dto);
	public List<MenuDto> getList(MenuDto dto); 
}
