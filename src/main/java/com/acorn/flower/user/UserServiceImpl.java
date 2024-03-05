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
	public List<UserDto> getUserList(UserDto dto) {
		List<UserDto> list = dao.getUserList(dto);
		return list;
	}

	@Override
	public UserDto getUser(String id) {
		UserDto result = dao.getUser(id);
		return result;
	}

	@Override
	public boolean insert(UserDto dto) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	    dto.setPassword(encoder.encode(dto.getPassword()));
		int result = dao.insert(dto);

		if (result != 1)
			return false;

		return true;
	}


	@Override
	public boolean delete(UserDto dto) {
		int result = dao.delete(dto);

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


}