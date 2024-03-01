package com.acorn.flower.superman;

import java.util.List;

import com.acorn.flower.user.UserDto;

public interface SuperDao {
	  public int ownerInsert(UserDto dto);
	    public int superInsert(UserDto dto);
	    public List<UserDto> getOwnerList(int rank);
	    public int delete(String id);
	    public int update(UserDto dto);
}
