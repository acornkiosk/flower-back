package com.acorn.flower.kiosk;

import java.util.List;

public interface KioskService {
	public boolean insert(KioskDto dto);

	public List<KioskDto> getList(KioskDto dto);

	public KioskDto getKiosk(KioskDto dto);

	public boolean delete(KioskDto dto);

	public boolean update(KioskDto dto);
	
	public KioskResponse selectPage(int pageNum);
}
