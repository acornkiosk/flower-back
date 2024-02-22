package com.acorn.flower.user;

import java.util.List;

public interface UserDao {
    public List<UserDto> getUserList();
    public UserDto getUser(String id);
    public int delete(String id);
    public int update(UserDto dto);
    public int insert(UserDto dto);

}
