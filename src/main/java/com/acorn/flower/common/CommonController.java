package com.acorn.flower.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonController {
	@Autowired
	private CommonService commonService;
	
	
}
