package com.acorn.flower.kiosk;

import java.util.List;

public interface KioskService {
	public boolean insert(KioskDto dto);

	public List<KioskDto> getList();

	public KioskDto getKiosk(int id);

	public KioskDto getLast();

	public boolean delete(int id);

	public boolean turnOn(int id);

	public boolean turnOff(int id);

	public boolean turnOnAll();

	public boolean turnOffAll();

	public boolean updateLocation(KioskDto dto);
}
