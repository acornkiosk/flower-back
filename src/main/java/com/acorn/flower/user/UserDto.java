package com.acorn.flower.user;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("userDto")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
	private String id;
	private String password;
	private String userName;
	private String rank;
	private String role;
	private String regdate;
}
