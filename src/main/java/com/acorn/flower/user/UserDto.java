package com.acorn.flower.user;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("userDto") // mapper xml 에서 사용할 type alias 설정
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
	private String id;
	private String password;
	private String rank;
}

