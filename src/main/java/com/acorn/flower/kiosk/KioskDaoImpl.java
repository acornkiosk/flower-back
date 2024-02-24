package com.acorn.flower.kiosk;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class KioskDaoImpl implements KioskDao {

	@Autowired
	private SqlSession session;

	@Override
	public int insert(KioskDto dto) {
		int result = session.insert("kiosk.insert", dto);
		return result;

	}

	@Override
	public List<KioskDto> getList() {
		List<KioskDto> list = session.selectList("kiosk.getList");
		return list;
	}

	@Override
	public int delete(KioskDto dto) {
		int result = session.delete("kiosk.delete", dto);
		return result;
	}

	@Override
	public KioskDto getKiosk(KioskDto dto) {
		KioskDto result = session.selectOne("kiosk.getKiosk", dto);
		return result;
	}

	@Override
	public int update(KioskDto dto) {
		int result = session.update("kiosk.update", dto);
		return result;
	}

}
