package com.acorn.flower.common;

import java.util.List;

public interface CommonService {
	public List<CommonDto> getChild(int code_id);
	public boolean insertRow(CommonDto dto);
	public boolean updateRow(CommonDto dto);
	public boolean deleteRow(int code_id);
	public CommonDto getCommon(int code_id);

}
