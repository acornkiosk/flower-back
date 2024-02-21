package com.acorn.flower.common;

import java.util.List;

import org.springframework.http.HttpStatusCode;

import com.acorn.flower.kiosk.KioskDto;
import com.acorn.flower.kiosk.KioskResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommonResponse {
	private CommonDto dto;
	private List<CommonDto> list;
	private HttpStatusCode status;
}
