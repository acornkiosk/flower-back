package com.acorn.flower.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl implements MenuService {
	@Autowired
	private MenuDao menuDao;

	@Override
	public boolean insert(MenuDto dto) {
		int result =menuDao.insert(dto);
		if(result!=1) return false;
		return true;
	}
}
