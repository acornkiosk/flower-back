package com.acorn.flower.kiosk;

import java.util.List;

public interface KioskDao {
	public int insert(KioskDto dto);
	public List<KioskDto> getList();
	public KioskDto getKiosk(int id);
	public int delete(int id);
}
