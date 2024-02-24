package com.acorn.flower.common;

import java.util.List;

public interface CommonDao {
	public List<CommonDto> getChild(CommonDto dto);

	public int insert(CommonDto dto);

	public int update(CommonDto dto);

	public int delete(CommonDto dto);

	public CommonDto getCommon(CommonDto dto);
}
