package com.acorn.flower.common;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("commonDto")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommonDto {
	private int code_id;
	private int p_code_id;
	private String code_name;
	private String code_value;
	private String code_img;
	
	public CommonDto(int code_id) {
		this.code_id = code_id;
	}
}
