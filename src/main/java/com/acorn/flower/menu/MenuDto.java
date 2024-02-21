package com.acorn.flower.menu;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Alias("menuDto")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MenuDto {
	private int id;
	private String name;
	private String img_name;
	private String summary;
	private String description;
	private String is_sold;
	private String category;
	private int category_id;	
}
