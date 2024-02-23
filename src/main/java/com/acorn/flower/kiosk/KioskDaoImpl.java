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
	public int delete(int id) {
		int result = session.delete("kiosk.delete", id);
		return result;
	}

	@Override
	public KioskDto getKiosk(int id) {
		KioskDto dto = session.selectOne("kiosk.getKiosk", id);
		return dto;
	}
	
	@Override
	public KioskDto getLast() {
		KioskDto dto = session.selectOne("kiosk.getLast");
		return dto;
	}

	@Override
	public int turnOn(int id) {
		int result = session.update("kiosk.turnOn", id);
		return result;
	}
	
	@Override
	public int turnOff(int id) {
		int result = session.update("kiosk.turnOff", id);
		return result;
	}

	@Override
	public int turnOnAll() {
		int result = session.update("kiosk.turnOnAll");
		return result;
	}

	@Override
	public int turnOffAll() {
		int result = session.update("kiosk.turnOffAll");
		return result;
	}

	@Override
	public int updateLocation(KioskDto dto) {
		int result = session.update("kiosk.updateLocation",dto);
		return result;
	}

}
