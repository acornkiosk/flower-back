package com.acorn.flower.user;

import java.util.List;

public interface UserService {
	public List<UserDto> getUserList();

	public UserDto getUser(String id);

	public boolean insert(UserDto dto);

	public boolean delete(String id);

	public boolean updateUser(UserDto dto);
	
	public boolean ownerInsert(UserDto dto);
	
	public boolean superInsert(UserDto dto);

}
