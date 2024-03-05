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
	public int ownerInsert(SuperDto dto) {
		int result = session.insert("super.ownerInsert",dto);
		return result;
	}

	@Override
	public int superInsert(SuperDto dto) {
		int result = session.insert("super.superInsert",dto);
		return result;
	}

	@Override
	public List<SuperDto> getOwnerList() {
		List<SuperDto> list = session.selectList("super.getOwnerList");
		return list;
	}

	@Override
	public int delete(String id) {
		int result = session.delete("super.delete", id);
		return result;
	}

	@Override

	public int update(SuperDto dto) {
		int result = session.update("super.ownerUpdate", dto);
		return result;
	}

	@Override
	public SuperDto getOwner(String id) {
		SuperDto dto=session.selectOne("super.getOwner",id);
		return dto;
	}
}
