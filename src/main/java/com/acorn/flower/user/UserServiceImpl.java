package com.acorn.flower.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    @Override
    public List<UserDto> getUserList() {
        List<UserDto> list = dao.getUserList();
        return list;
    }

    @Override
    public UserDto getUser(String id) { // UserDetails 때문에 유일하게 dto 안함 
        UserDto dto = dao.getUser(id);
        return dto;
    }

    @Override
    public boolean insert(UserDto dto) {
        int result = dao.insert(dto);

        if (result != 1) return false;

        return true;
    }

    @Override
    public boolean delete(UserDto dto) {
        int result = dao.delete(dto);

        if(result != 1) return false;

        return true;
    }

    @Override
    public boolean updateUser(UserDto dto) {
        int result = dao.update(dto);

        if(result == 0) return false;

        return true;
    }
}