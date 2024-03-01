package com.acorn.flower.superman;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.acorn.flower.user.UserDto;

@Service
public class SuperServiceImpl implements SuperService{

	@Autowired
	private SuperDao dao;

	@Override
	public boolean ownerInsert(SuperDto dto) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		dto.setPassword(encoder.encode(dto.getPassword()));
		
		int result = dao.ownerInsert(dto);

		if (result == 0)
			return false;

		return true;
	}


	@Override
	public boolean superInsert(SuperDto dto) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		dto.setPassword(encoder.encode(dto.getPassword()));
		
		int result = dao.superInsert(dto);

	
		if (result == 0)
			return false;

		return true;
	}


	@Override
	public List<SuperDto> getOwnerList() {
		List<SuperDto> list = dao.getOwnerList();
		return list;
	}


	@Override
	public boolean delete(String id) {
		int result = dao.delete(id);

		if (result != 1)
			return false;

		return true;
	}


	@Override
	public boolean update(SuperDto dto) {
		int result = dao.update(dto);

		if (result != 1)
			return false;

		return true;
	}


	@Override
	public SuperDto getOwner(String id) {
		SuperDto dto=dao.getOwner(id);
		return dto;
	}

}
