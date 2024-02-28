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
	public boolean ownerInsert(UserDto dto) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		dto.setPassword(encoder.encode(dto.getPassword()));
		
		int result = dao.ownerInsert(dto);

		if (result == 0)
			return false;

		return true;
	}


	@Override
	public boolean superInsert(UserDto dto) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		dto.setPassword(encoder.encode(dto.getPassword()));
		
		int result = dao.superInsert(dto);

	
		if (result == 0)
			return false;

		return true;
	}


	@Override
	public List<UserDto> getOwnerList() {
		int rank=3002;
		List<UserDto> list = dao.getOwnerList(rank);
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
	public boolean update(UserDto dto) {
		int result = dao.update(dto);

		if (result != 1)
			return false;

		return true;
	}


	@Override
	public UserDto getOwner(String id) {
		UserDto dto=dao.getOwner(id);
		return dto;
	}

}
