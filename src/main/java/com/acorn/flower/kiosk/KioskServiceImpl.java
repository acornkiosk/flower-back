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

		if (result != 1)
			return false;

		return true;
	}

	@Override
	public List<KioskDto> getList() {
		List<KioskDto> list = dao.getList();

		return list;
	}

	@Override
	public boolean delete(KioskDto dto) {
		int result = dao.delete(dto);

		if (result != 1)
			return false;

		return true;
	}

	@Override
	public KioskDto getKiosk(KioskDto dto) {
		KioskDto result = dao.getKiosk(dto);

		if (result == null)
			return null;

		return result;
	}

	@Override
	public boolean update(KioskDto dto) {
		int result = dao.update(dto);

		if (result == 0)
			return false;

		return true;
	}

}
