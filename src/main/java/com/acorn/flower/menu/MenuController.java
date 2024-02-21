package com.acorn.flower.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class MenuController {
	@Autowired
	private MenuService menuService;
	
	@PostMapping("/api/menu")
	public ResponseEntity<MenuResponse> addMenu(@RequestBody MenuDto dto){
		
		return null;
	}
	
}
