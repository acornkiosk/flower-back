package com.acorn.flower.kiosk;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KioskServiceImpl implements KioskService {

	@Autowired
	private KioskDao dao;

	@Override
	public boolean insert(KioskDto dto) {
		int result = dao.insert(dto);

		if (result != 1) return false;
		
		return true;
	}

	@Override
	public List<KioskDto> getList() {
		List<KioskDto> list = dao.getList();
		
		return list;
	}

	@Override
	public boolean delete(int id) {
		int result = dao.delete(id);
		
		if(result != 1) return false;
		
		return true;
	}

	@Override
	public KioskDto getKiosk(int id) {
		KioskDto dto = dao.getKiosk(id);
		
		if(dto == null) return null;
		
		return dto;
	}

}
