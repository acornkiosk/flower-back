package com.acorn.flower.user;

import java.util.List;

public interface UserDao {
    public List<UserDto> getUserList();
    public UserDto getUser(String id);
    public int insert(UserDto dto);
    public int delete(UserDto dto);
    public int update(UserDto dto);
}
