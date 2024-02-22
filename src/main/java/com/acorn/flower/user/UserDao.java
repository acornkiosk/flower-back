package com.acorn.flower.user;

import java.util.List;

public interface UserDao {
	public String insertUser(UserDto dto);
	public String deleteUser(UserDto dto);
	public String updateUser(UserDto dto);
	public List<UserDto> getList();
	public UserDto getUser(UserDto dto);
}
