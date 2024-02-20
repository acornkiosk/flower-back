package com.acorn.flower.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuController {
	@Autowired
	private MenuService menuService;
	
}
