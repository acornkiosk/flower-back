package com.acorn.flower.user;

import java.util.List;

public interface UserService {
    public List<UserDto> getUserList();
    public UserDto getUser(String id); // UserDetails 때문에 유일하게 dto 안함 
    public boolean insert(UserDto dto);
    public boolean delete(UserDto dto);
    public boolean updateUser(UserDto dto);
}
