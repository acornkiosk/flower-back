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
	public List<UserDto> getUserList(UserDto dto) {
		List<UserDto> list = session.selectList("users.getUserList");
		return list;
	}

	@Override
	public UserDto getUser(String id) {
		UserDto dto = session.selectOne("users.getUser", id);
		return dto;
	}


	@Override
	public int delete(String id) {
		int result = session.delete("users.delete", id);
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

	@Override
	public int getCount() {
		int result = session.selectOne("users.getCount");
		return result;
	}


}
