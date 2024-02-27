package com.acorn.flower.superman;

import java.util.List;

import com.acorn.flower.user.UserDto;

public interface SuperService {
	public boolean ownerInsert(UserDto dto);
	
	public boolean superInsert(UserDto dto);
	
	public List<UserDto> getOwnerList();
	
	public boolean delete(String id);
}
