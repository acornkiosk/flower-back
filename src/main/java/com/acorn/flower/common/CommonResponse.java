package com.acorn.flower.common;

import java.util.List;

import org.springframework.http.HttpStatusCode;

public class CommonResponse {
	private CommonDto dto;
	private List<CommonDto> list;
	private HttpStatusCode status;
}
