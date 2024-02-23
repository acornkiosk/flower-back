package com.acorn.flower.user;
import java.util.List;

public interface UserDao {
	public void insert(UserDto dto);
	public void update(UserDto dto);
	public void delete(String id);
	public UserDto getUser(String id);
	public List<UserDto> getUserList();
}