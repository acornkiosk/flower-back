package com.acorn.flower.user;

import lombok.Builder;
import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


@Alias("userDto")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
	private String userName;
	private String id;
	private String password;
	private int rank;
	private String role;
	private String regdate;
}

