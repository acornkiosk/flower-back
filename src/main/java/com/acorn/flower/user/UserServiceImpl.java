package com.acorn.flower.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;

	@Override
	public List<UserDto> getUserList() {
		List<UserDto> list = dao.getUserList();
		return list;
	}


	@Override
	public UserDto getUser(String id) {
		UserDto dto = dao.getUser(id);
		return dto;
	}

	@Override
	public boolean insert(UserDto dto) {
		int result = dao.insert(dto);

		if (result != 1)
			return false;

		return true;
	}


	@Override
	public boolean delete(String id) {
		int result = dao.delete(id);

		if (result != 1)
			return false;

		return true;
	}

	@Override
	public boolean updateUser(UserDto dto) {
		int result = dao.update(dto);

		if (result == 0)
			return false;

		return true;
	}


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
		
		int result = dao.ownerInsert(dto);

		int result = dao.superInsert(dto);

		if (result == 0)
			return false;

		return true;
	}
}