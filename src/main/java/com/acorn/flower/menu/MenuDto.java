package com.acorn.flower.menu;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("menuDto")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MenuDto implements Serializable{
	private int id;
	private String name;
	private String img_name;
	private int price;
	private String summary;
	private String description;
	private String is_sold;
	private int category_id;
	
	
	private String category;
	private transient MultipartFile image; //이미지 파일 업로드 처리를 위한 필드
	private int pageNum; //페이지 번호를 나타내는 필드
	private int startRowNum; //페이지 네이션 처리를 위한 필드
	private int endRowNum; //페이지 네이션 처리를 위한 필드
}
