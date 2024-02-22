package com.acorn.flower.kiosk;

import java.util.List;

public interface KioskDao {
	public int insert(KioskDto dto);

	public List<KioskDto> getList();

	public KioskDto getKiosk(int id);

	public KioskDto getLast();

	public int delete(int id);

	public int turnOn(int id);

	public int turnOff(int id);

	public int turnOnAll();

	public int turnOffAll();

	public int updateLocation(KioskDto dto);
}
