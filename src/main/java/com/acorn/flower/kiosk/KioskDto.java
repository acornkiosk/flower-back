package com.acorn.flower.kiosk;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("kioskDto")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class KioskDto {
	private int id;
	private String location;
	private String power;
}
