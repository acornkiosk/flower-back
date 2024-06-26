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
		List<UserDto> list = session.selectList("users.getUserList",dto);
		return list;
	}
	@Override
	public UserDto getUser(String id) {
		UserDto result = session.selectOne("users.getUser", id);
		return result;
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
	@Override
	public int getCount() {
		int result = session.selectOne("users.getCount");
		return result;
	}
	@Override
	public String checkId(UserDto dto) {
		String result = session.selectOne("users.checkId",dto);
		return result;
	}
}
