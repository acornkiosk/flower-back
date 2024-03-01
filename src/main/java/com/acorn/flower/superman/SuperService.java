package com.acorn.flower.superman;

import java.util.List;

import com.acorn.flower.user.UserDto;

public interface SuperService {
	public boolean ownerInsert(SuperDto dto);
	
	public boolean superInsert(SuperDto dto);
	
	public List<SuperDto> getOwnerList();
	
	public boolean delete(String id);
	
	public SuperDto getOwner(String id);
	
	public boolean update(SuperDto dto);
}
