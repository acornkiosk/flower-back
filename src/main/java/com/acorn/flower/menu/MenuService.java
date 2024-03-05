package com.acorn.flower.menu;

import java.util.List;
import java.util.Map;

public interface MenuService {
	public boolean insert(MenuDto dto);
	public Map<String,Object> getList(MenuDto dto); 
	public MenuDto getMenu(MenuDto dto);
	public boolean update(MenuDto dto);
	public boolean delete(MenuDto dto);
	
}
