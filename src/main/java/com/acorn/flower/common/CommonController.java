package com.acorn.flower.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CommonController {
	@Autowired
	private CommonService service;
	
	
	@PostMapping("/api/common/child")
	public ResponseEntity<CommonResponse> getCategory(@RequestBody int code_id) {
		CommonResponse response = new CommonResponse();
		try {
			List<CommonDto> list = service.getChild(code_id);
			if (!list.isEmpty()) {
				for (CommonDto dto : list) {
					log.info("category = {}", dto.toString());
				}
				response.setList(list);
				response.setStatus(HttpStatus.OK);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				log.error("카테고리가 없습니다");
				response.setList(list);
				response.setStatus(HttpStatus.BAD_REQUEST);
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			log.error("서버에 문제가 있습니다.");
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
