package com.acorn.flower.user;

import java.util.List;

public interface UserService {

	public List<UserDto> getUserList(UserDto dto);

	public UserResponse selectPage(int pageNum);

	public UserDto getUser(String id);

	public boolean insert(UserDto dto);

	public boolean delete(String id);

	public boolean updateUser(UserDto dto);
	



}
