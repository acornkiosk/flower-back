package com.acorn.flower.menu;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("imageDto")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ImageDto {
  private String name;
}
