package com.acorn.flower.user;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
	public int ownerInsert(UserDto dto) {
		int result = session.insert("users.ownerInsert",dto);
		return result;
	}

	@Override
	public int superInsert(UserDto dto) {
		int result = session.insert("users.superInsert",dto);
		return result;
	}

	@Override
	public List<UserDto> getOwnerList() {
		List<UserDto> list = session.selectList("users.getOwnerList");
		return list;
	}
}
