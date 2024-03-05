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
	 * code_id = 0 이면 전체 조회 
	 * code_id != 0 이면 P_CODE_ID = code_id 조회
	 * @param code_id
	 * @return
	 */	
	@PostMapping("/api/common/child")
	public ResponseEntity<CommonResponse> getChild(@RequestBody CommonDto dto) {
		CommonResponse response = new CommonResponse();
		try {
			List<CommonDto> list = service.getChild(dto);
			if (!list.isEmpty()) {
				for (CommonDto dto2 : list) {
					log.info("category = {}", dto2.toString());
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

	/**
	 * 단일 정보 조회
	 * code_id 필요
	 * @param code_id
	 * @return
	 */
	@PostMapping("/api/common/get")
	public ResponseEntity<CommonResponse> getCommon(@RequestBody CommonDto dto) {
		CommonResponse response = new CommonResponse();
		try {
			CommonDto dto2 = service.getCommon(dto);
			if (dto2 != null) {
				log.info("category = {}", dto2.toString());
				response.setDto(dto2);
				response.setStatus(HttpStatus.OK);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				log.error(dto.getCode_id() + "번 데이터가 없습니다");		
				response.setStatus(HttpStatus.BAD_REQUEST);
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("서버에 문제가 있습니다.");
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * 공통코드 추가
	 * code_id,p_code_id,code_name,code_value,code_img 필요
	 * @param dto
	 * @return
	 */
	@PostMapping("/api/common/insert")
	public ResponseEntity<CommonResponse> addCommon(@RequestBody CommonDto dto) {
		CommonResponse response = new CommonResponse();
		System.out.println(dto);
		try {
			boolean isSuccess = service.insertRow(dto);

			if (isSuccess) {
				log.info("common = {}", dto.toString());
				response.setDto(dto);
				response.setStatus(HttpStatus.OK);
				return new ResponseEntity<CommonResponse>(response, HttpStatus.OK);
			} else {
				log.error("데이터가 추가되지 않았습니다");
				response.setStatus(HttpStatus.BAD_REQUEST);
				return new ResponseEntity<CommonResponse>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("서버에 문제가 있습니다");
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<CommonResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 공통코드 수정
	 * 
	 * @param dto
	 * @return
	 */
	@PostMapping("/api/common/update")
	public ResponseEntity<CommonResponse> updateCommon(@RequestBody CommonDto dto) {
		CommonResponse response = new CommonResponse();
		try {
			boolean isSuccess = service.updateRow(dto);
			if (isSuccess) {
				log.info("common = {}", dto.toString());
				response.setDto(dto);
				response.setStatus(HttpStatus.OK);
				return new ResponseEntity<CommonResponse>(response, HttpStatus.OK);
			} else {
				log.error("데이터가 업데이트되지 않았습니다");
				response.setStatus(HttpStatus.BAD_REQUEST);
				return new ResponseEntity<CommonResponse>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			log.error("서버에 연결되지 않았습니다");
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<CommonResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 공통코드 삭제
	 * code_id 필요
	 * @param dto
	 * @return
	 */
	@PostMapping("/api/common/delete")
	public ResponseEntity<CommonResponse> deleteCommon(@RequestBody CommonDto dto) {
		CommonResponse response = new CommonResponse();
		try {
			CommonDto dto2 = service.getCommon(dto);
			boolean isSuccess = service.deleteRow(dto);
			if (isSuccess) {
				log.info("common = {}", dto2);
				response.setDto(dto2);
				response.setStatus(HttpStatus.OK);
				return new ResponseEntity<CommonResponse>(response, HttpStatus.OK);
			} else {
				log.error("데이터가 삭제되지 않았습니다");
				response.setStatus(HttpStatus.BAD_REQUEST);
				return new ResponseEntity<CommonResponse>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			log.error("서버에 연결되지 않았습니다");
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<CommonResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
