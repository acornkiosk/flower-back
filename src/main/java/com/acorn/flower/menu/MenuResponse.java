package com.acorn.flower.menu;

import java.util.List;

import org.springframework.http.HttpStatusCode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MenuResponse {
	private MenuDto dto;
	private List<MenuDto> list;
	private int pageNum;
	private int startPageNum;
	private int endPageNum;
	private int totalPageCount;
	private HttpStatusCode status;

}
