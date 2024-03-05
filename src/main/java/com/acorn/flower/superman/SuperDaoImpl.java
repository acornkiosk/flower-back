package com.acorn.flower.superman;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.acorn.flower.user.UserDto;

@Repository
public class SuperDaoImpl implements SuperDao{
	@Autowired
	private SqlSession session;
	
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
	public List<UserDto> getOwnerList(int rank) {
		List<UserDto> list = session.selectList("users.getOwnerList",rank);
		return list;
	}

	@Override
	public int delete(String id) {
		int result = session.delete("users.delete", id);
		return result;
	}

	@Override
	public int update(UserDto dto) {
		int result = session.update("users.update", dto);
		return result;
	}

	@Override
	public UserDto getOwner(String id) {
		UserDto dto=session.selectOne("users.getUser",id);
		return dto;
	}
}
