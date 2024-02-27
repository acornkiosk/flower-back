package com.acorn.flower.superman;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.acorn.flower.user.UserDto;


@RestController
public class SuperController {
	//SecurityConfig 에서 Bean 으로 등록한 객체


	
    @Autowired
    private SuperService service;
    
	@PostMapping("/super/ownerInsert")
	public String ownerInsert(@RequestBody UserDto dto) {
		boolean isSuccess=service.ownerInsert(dto);
		if(isSuccess) {
			return dto.getId();
		}else {
			return "실패";
		}
	}
	
	@PostMapping("/superInsert")
	public String superInsert(@RequestBody UserDto dto) {
		boolean isSuccess=service.superInsert(dto);
		if(isSuccess) {
			return dto.getId();
		}else {
			return "실패";
		}
	}
	@GetMapping("/super/ownerList")
	public List<UserDto> ownerList() {
		List<UserDto> list=service.getOwnerList();
		System.out.println(list);
		if(list !=null) {
			return list;
		}else {
			return null;
		}
	}
	
	@DeleteMapping("/super/ownerDelete/{id}")
	public String ownerDelete(@PathVariable String id) {
		//mapper 삭제 처리하기
		boolean isSuccess=service.delete(id);
		if(isSuccess) {
			return id;
		}else {
			return "실패";
		}
	}
	
}
