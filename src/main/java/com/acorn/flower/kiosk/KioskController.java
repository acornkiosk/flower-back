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
			isSuccess = service.insert(dto);
			if (isSuccess) {
				log.info("kiosk = {}", dto.toString());
				KioskDto insertedDto = service.getLast();
				response.setDto(insertedDto);
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
	@GetMapping("/api/kiosk/list")
	public ResponseEntity<KioskResponse> getList() {
		KioskResponse response = new KioskResponse();
		try {
			List<KioskDto> list = service.getList();
			if (!list.isEmpty()) {
				for (KioskDto dto : list) {
					log.info("kiosk = {}", dto.toString());
				}
				response.setList(list);
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
	public ResponseEntity<KioskResponse> getKiosk(@RequestBody int id) {
		KioskResponse response = new KioskResponse();
		try {
			KioskDto dto = service.getKiosk(id);
			if (dto != null) {
				log.info("kiosk = {}", dto.toString());
				response.setDto(dto);
				response.setStatus(HttpStatus.OK);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				log.error(id + "번 데이터는 없습니다.");
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
	public ResponseEntity<KioskResponse> delete(@RequestBody int id) {
		boolean isSuccess;
		KioskResponse response = new KioskResponse();
		try {
			KioskDto dto = service.getKiosk(id);
			isSuccess = service.delete(id);
			if (isSuccess) {
				log.info(id + "번 삭제됨");
				response.setDto(dto);
				response.setStatus(HttpStatus.OK);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				log.error(id + "번은 없는 데이터 입니다.");
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
	 * 키오스크 전원 켜기 body : kiosk id
	 * 
	 * @param id
	 * @return
	 */
	@PostMapping("/api/kiosk/turnOn")
	public ResponseEntity<KioskResponse> turnOn(@RequestBody int id) {
		boolean isSuccess;
		KioskResponse response = new KioskResponse();
		try {
			isSuccess = service.turnOn(id);
			KioskDto dto = service.getKiosk(id);
			if (isSuccess) {
				log.info(id + "번 전원 켜짐");
				response.setDto(dto);
				response.setStatus(HttpStatus.OK);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				log.error(id + "번 전원이 이미 켜져있거나 오류가 있습니다.");
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
	 * 키오스크 전원 끄기 body : kiosk id
	 * 
	 * @param id
	 * @return
	 */
	@PostMapping("/api/kiosk/turnOff")
	public ResponseEntity<KioskResponse> turnOff(@RequestBody int id) {
		boolean isSuccess;
		KioskResponse response = new KioskResponse();
		try {
			isSuccess = service.turnOff(id);
			KioskDto dto = service.getKiosk(id);
			if (isSuccess) {
				log.info(id + "번 전원 꺼짐");
				response.setDto(dto);
				response.setStatus(HttpStatus.OK);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				log.error(id + "번 전원이 이미 꺼져있거나 오류가 있습니다.");
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
	 * 키오스크 전체 전원 켜기 body x
	 * 
	 * @return
	 */
	@PostMapping("/api/kiosk/turnOnAll")
	public ResponseEntity<KioskResponse> turnOnAll() {
		boolean isSuccess;
		KioskResponse response = new KioskResponse();
		try {
			isSuccess = service.turnOnAll();
			List<KioskDto> list = service.getList();
			if (isSuccess) {
				log.info("전체 전원 켜짐");
				response.setList(list);
				response.setStatus(HttpStatus.OK);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				log.error("전체 전원이 다 켜져 있거나 오류가 있습니다.");
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
	 * 키오스크 전체 전원 끄기 body x
	 * 
	 * @return
	 */
	@PostMapping("/api/kiosk/turnOffAll")
	public ResponseEntity<KioskResponse> turnOffAll() {
		boolean isSuccess;
		KioskResponse response = new KioskResponse();
		try {
			isSuccess = service.turnOffAll();
			List<KioskDto> list = service.getList();
			if (isSuccess) {
				log.info("전체 전원 꺼짐");
				response.setList(list);
				response.setStatus(HttpStatus.OK);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				log.error("전체 전원이 다 꺼져 있거나 오류가 있습니다.");
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
	 * 키오스크 위치 변경 body: kiosk id, location
	 * 
	 * @param dto
	 * @return
	 */
	@PostMapping("/api/kiosk/update/location")
	public ResponseEntity<KioskResponse> updateLocation(@RequestBody KioskDto dto) {
		boolean isSuccess;
		KioskResponse response = new KioskResponse();
		try {
			isSuccess = service.updateLocation(dto);
			KioskDto changedDto = service.getKiosk(dto.getId());
			if (isSuccess) {
				log.info(dto.getId() + "번 위치 " + dto.getLocation() + "로 바뀜");
				response.setDto(changedDto);
				response.setStatus(HttpStatus.OK);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				log.error("위치가 변경되지 않았습니다.");
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
