package com.acorn.flower.user;

import java.util.List;

public interface UserService {
    public List<UserDto> getUserList(UserDto dto);

    public UserDto getUser(String id);

    public boolean insert(UserDto dto);

    public boolean delete(UserDto dto);

    public boolean updateUser(UserDto dto);

    public UserResponse selectPage(int pageNum);
}
