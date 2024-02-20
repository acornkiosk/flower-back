package com.acorn.flower.kiosk;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KioskController {

	@GetMapping("/api/test")
	public String test() {
		return "test";
	}
}
