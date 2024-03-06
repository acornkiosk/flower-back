package com.acorn.flower.menu;

public interface MenuService {
	public boolean insert(MenuDto dto);

	public MenuResponse getList(MenuDto dto);

	public MenuDto getMenu(MenuDto dto);

	public boolean update(MenuDto dto);

	public boolean delete(MenuDto dto);

}
