package com.acorn.flower.kiosk;

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
 * 키오스크 관리
 */
@Slf4j
@RestController
public class KioskController {

	@Autowired
	private KioskService service;

	/**
	 * 키오스크 추가 body: 키오스크 dto
	 * 
	 * @param dto
	 * @return
	 */
	@PostMapping("/api/kiosk")
	public ResponseEntity<KioskResponse> addKiosk(@RequestBody KioskDto dto) {
		boolean isSuccess;
		KioskResponse response = new KioskResponse();
		try {
			dto.setPower("off");
			isSuccess = service.insert(dto);
			List<KioskDto> list = service.getList(dto);
			if (isSuccess) {
				log.info("kiosk = {}", dto.toString());
				// 최근 추가한 dto를 얻어오는 로직 작성
				response.setDto(dto);
				response.setList(list);
				response.setStatus(HttpStatus.OK);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				log.error("데이터 insert 실패");
				response.setDto(dto);
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
	 * 키오스크 리스트 조회 param x
	 * 
	 * @return
	 */
	@PostMapping("/api/kiosk/list")
	public ResponseEntity<KioskResponse> getList(@RequestBody int pageNum) {
		KioskResponse response = new KioskResponse();
		try {
			response = service.selectPage(pageNum);
			if (!response.getList().isEmpty()) {
				for (KioskDto dto : response.getList()) {
					log.info("kiosk = {}", dto.toString());
				}
				response.setStatus(HttpStatus.OK);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				log.error("데이터 getList 실패");
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
	 * 키오스크 조회 body : kiosk id
	 * 
	 * @param id
	 * @return
	 */
	@PostMapping("/api/kiosk/get")
	public ResponseEntity<KioskResponse> getKiosk(@RequestBody KioskDto dto) {
		KioskResponse response = new KioskResponse();
		try {
			KioskDto result = service.getKiosk(dto);
			if (dto != null) {
				log.info("kiosk = {}", result.toString());
				response.setDto(result);
				response.setStatus(HttpStatus.OK);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				log.error(dto.getId() + "번 데이터는 없습니다.");
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
	 * 키오스크 삭제 body : kiosk id
	 * 
	 * @param id
	 * @return
	 */
	@PostMapping("/api/kiosk/delete")
	public ResponseEntity<KioskResponse> delete(@RequestBody KioskDto dto) {
		boolean isSuccess;
		KioskResponse response = new KioskResponse();
		try {
			KioskDto result = service.getKiosk(dto);
			isSuccess = service.delete(dto);
			if (isSuccess) {
				log.info(dto.getId() + "번 삭제됨");
				response.setDto(result);
				response.setStatus(HttpStatus.OK);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				log.error(dto.getId() + "번은 없는 데이터 입니다.");
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
	 * 키오스크 정보 변경 body: kiosk id, location, power
	 * 
	 * @param dto
	 * @return
	 */
	@PostMapping("/api/kiosk/update")
	public ResponseEntity<KioskResponse> updateKiosk(@RequestBody KioskDto dto) {
		boolean isSuccess;
		KioskResponse response = new KioskResponse();
		try {
			isSuccess = service.update(dto);
			KioskDto changedDto = service.getKiosk(dto);
			if (isSuccess) {
				log.info("kioskDto = {}", dto);
				response.setDto(changedDto);
				response.setStatus(HttpStatus.OK);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				log.error("정보가 변경되지 않았습니다.");
				response.setDto(dto);
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
