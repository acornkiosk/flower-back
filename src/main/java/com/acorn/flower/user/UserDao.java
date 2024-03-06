package com.acorn.flower.user;

import java.util.List;

public interface UserDao {
    public List<UserDto> getUserList(UserDto dto);
    public UserDto getUser(String id);
    public int insert(UserDto dto);
    public int delete(String id);
    public int update(UserDto dto);
    public int getCount();
  

}

