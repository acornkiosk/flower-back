package com.acorn.flower.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonServiceImpl implements CommonService {
	@Autowired
	private CommonDao commonDao;
}
