package com.acorn.flower.superman;

import java.util.List;

import com.acorn.flower.user.UserDto;

public interface SuperDao {
	  public int ownerInsert(SuperDto dto);
	    public int superInsert(SuperDto dto);
	    public List<SuperDto> getOwnerList();
	    public int delete(String id);
	    public SuperDto getOwner(String id);
	    public int update(SuperDto dto);
}
