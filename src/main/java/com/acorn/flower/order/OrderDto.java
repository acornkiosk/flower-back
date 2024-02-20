package com.acorn.flower.order;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("orderDto")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDto {
	private int id;
	private int kiosk_id;
	private String menu_name;
	private int menu_price;
	private int menu_count;
	private String options;
	private String regdate;
}
