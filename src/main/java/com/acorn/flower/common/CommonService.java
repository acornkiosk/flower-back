package com.acorn.flower.common;

import java.util.List;

public interface CommonService {
	public List<CommonDto> getChild(CommonDto dto);
	public boolean insertRow(CommonDto dto);
	public boolean updateRow(CommonDto dto);
	public boolean deleteRow(CommonDto dto);
	public CommonDto getCommon(CommonDto dto);

}
