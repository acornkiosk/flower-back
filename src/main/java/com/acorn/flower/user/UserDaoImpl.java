package com.acorn.flower.user;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SqlSession session;

    @Override
    public List<UserDto> getUserList() {
        List<UserDto> list = session.selectList("users.getUserList");
        return list;
    }

    @Override
    public UserDto getUser(String id) {
        UserDto dto = session.selectOne("users.getUser", id);
        return dto;
    }

    @Override
    public int delete(UserDto dto) {
        int result = session.delete("users.delete", dto);
        return result;
    }

    @Override
    public int insert(UserDto dto) {
        int result = session.insert("users.insert", dto);
        return result;
    }

    @Override
    public int update(UserDto dto) {
        int result = session.update("users.update", dto);
        return result;
    }
}
