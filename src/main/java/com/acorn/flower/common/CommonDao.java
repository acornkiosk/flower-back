package com.acorn.flower.common;

import java.util.List;

public interface CommonDao {
	public List<CommonDto> getChild(int code_id);
	public int insert(CommonDto dto);
	public int update(CommonDto dto);
	public int delete(int code_id);
	public CommonDto getRow(int code_id);
}
