package com.acorn.flower.common;

import java.util.List;

import org.springframework.http.HttpStatusCode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommonResponse {
	private CommonDto dto;
	private List<CommonDto> list;
	private HttpStatusCode status;
}
