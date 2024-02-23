package com.acorn.flower.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * 공통 코드 관리
 */
@Slf4j
@RestController
public class CommonController {
	@Autowired
	private CommonService service;
	
	/**
	 * 하위 정보 리스트 조회
	 * @param code_id
	 * @return
	 */
	@GetMapping("/test")
	public String test() {
		return "testgood";
	}
	
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

	@PostMapping("/api/common/insert")
	public ResponseEntity<CommonResponse> addCommon(@RequestBody CommonDto dto) {
		CommonResponse response = new CommonResponse();
		
		try {
			boolean isSuccess = service.insertRow(dto);
			if(isSuccess) {
				log.info("common = {}" , dto.toString());
				response.setDto(dto);
				response.setStatus(HttpStatus.OK);
				return new ResponseEntity<CommonResponse>(response,HttpStatus.OK);
			}else {
				log.error("데이터가 추가되지 않았습니다");
				response.setStatus(HttpStatus.BAD_REQUEST);
				return new ResponseEntity<CommonResponse>(response,HttpStatus.BAD_REQUEST);
			}
		}catch (Exception e) {
			log.error("서버에 문제가 있습니다");
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<CommonResponse>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/api/common/update")
	public ResponseEntity<CommonResponse> updateCommon(@RequestBody CommonDto dto){
		CommonResponse response = new CommonResponse();
		
		try {
			boolean isSuccess = service.updateRow(dto);
			if(isSuccess) {
				log.info("common = {}", dto.toString());
				response.setDto(dto);
				response.setStatus(HttpStatus.OK);
				return new ResponseEntity<CommonResponse>(response,HttpStatus.OK);
			}else {
				log.error("데이터가 업데이트되지 않았습니다");
				response.setStatus(HttpStatus.BAD_REQUEST);
				return new ResponseEntity<CommonResponse>(response, HttpStatus.BAD_REQUEST);
			}
		}catch(Exception e) {
			log.error("서버에 연결되지 않았습니다");
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<CommonResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/api/common/delete")
	public ResponseEntity<CommonResponse> deleteCommon(@RequestBody int code_id){
		CommonResponse response = new CommonResponse();
		
		try {
			boolean isSuccess = service.deleteRow(code_id);
			if(isSuccess) {
				log.info("common = {}", code_id);
				response.setStatus(HttpStatus.OK);
				return new ResponseEntity<CommonResponse>(response,HttpStatus.OK);
			}else {
				log.error("데이터가 삭제되지 않았습니다");
				response.setStatus(HttpStatus.BAD_REQUEST);
				return new ResponseEntity<CommonResponse>(response, HttpStatus.BAD_REQUEST);
			}
		}catch(Exception e) {
			log.error("서버에 연결되지 않았습니다");
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<CommonResponse>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
