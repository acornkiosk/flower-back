package com.acorn.flower.kiosk;

import java.util.List;

public interface KioskDao {
	public int insert(KioskDto dto);

	public List<KioskDto> getList();

	public KioskDto getKiosk(KioskDto dto);

	public int delete(KioskDto dto);

	public int update(KioskDto dto);
}
