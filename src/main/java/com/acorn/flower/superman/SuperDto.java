package com.acorn.flower.superman;


import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("superDto")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SuperDto {
	private String userName;
	private String id;
	private String password;
	private int rank;
	private String role;
	private String regdate;
	private String newId;
}

