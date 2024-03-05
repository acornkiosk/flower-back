package com.acorn.flower.menu;

import java.io.Serializable;
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
public class MenuResponse{
	private MenuDto dto;
	private List<MenuDto> list;
	private int pageNum;
	private int startPageNum;
	private int endPageNum;
	private int totalPageCount;
	
	private HttpStatusCode status;
	
	
}
