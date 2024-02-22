package com.acorn.flower.menu;

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
public class MenuController {
	@Autowired
	private MenuService menuService;

	/**
	 * 메뉴 수정
	 * 
	 * @param dto
	 * @return
	 */
	@PostMapping("/api/menu/update")
	public ResponseEntity<MenuResponse> updateMenu(@RequestBody MenuDto dto) {
		boolean isSuccess;
		MenuResponse response = new MenuResponse();
		try {
			isSuccess = menuService.update(dto);
			if (isSuccess) {
				log.info("menu = {}", dto.toString());
				response.setDto(dto);
				response.setStatus(HttpStatus.OK);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				log.error("menu 데이터 update 실패");
				response.setDto(dto);
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
	 * menu 삭제
	 * 
	 * @param id
	 * @return
	 */
	@PostMapping("/api/menu/delete")
	public ResponseEntity<MenuResponse> deleteMenu(@RequestBody int id) {
		boolean isSuccess;
		MenuResponse response = new MenuResponse();
		try {
			MenuDto dto = menuService.getMenu(id);
			isSuccess = menuService.delete(id);
			if (isSuccess) {
				log.info("menu = {}", dto.toString());
				response.setDto(dto);
				response.setStatus(HttpStatus.OK);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				log.error("menu데이터 delete 실패");
				response.setDto(dto);
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
	 * menu 가져오기
	 * 
	 * @param id
	 * @return dto
	 */
	@PostMapping("/api/menu/get")
	public ResponseEntity<MenuResponse> getMenu(@RequestBody int id) {
		MenuResponse response = new MenuResponse();
		try {
			MenuDto dto = menuService.getMenu(id);
			System.out.println(dto);
			if (dto != null) {
				log.info("menu = {}", dto.toString());
				response.setDto(dto);
				response.setStatus(HttpStatus.OK);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				log.error(id + "번 데이터는 없습니다.");
				response.setDto(dto);
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
	 * 카테고리별 or 전체 리스트 가져오기
	 * 
	 * @param dto
	 * @return list
	 */
	@PostMapping("/api/menu/list")
	public ResponseEntity<MenuResponse> getMenuList(@RequestBody MenuDto dto) {

		MenuResponse response = new MenuResponse();
		try {
			List<MenuDto> list = menuService.getList(dto);
			System.out.println(list);
			if (!list.isEmpty()) {
				for (MenuDto dto2 : list) {
					log.info("menu = {}", dto2.toString());
				}

				response.setList(list);
				response.setStatus(HttpStatus.OK);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				log.error("menu데이터 getList 실패");
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
	 * 메뉴 등록
	 * 
	 * @param dto
	 * @return
	 */
	@PostMapping("/api/menu")
	public ResponseEntity<MenuResponse> addMenu(@RequestBody MenuDto dto) {
		boolean isSuccess;
		MenuResponse response = new MenuResponse();
	
		try {
			isSuccess = menuService.insert(dto);
			MenuDto insertDto= menuService.getLast();
			if (isSuccess) {
				
				log.info("menu = {}", insertDto.toString());
				response.setDto(insertDto);
				response.setStatus(HttpStatus.OK);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				log.error("menu데이터 insert 실패");
				response.setDto(insertDto);
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

}
