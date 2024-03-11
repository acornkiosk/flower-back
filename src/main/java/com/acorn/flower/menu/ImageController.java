package com.acorn.flower.menu;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * 이미지 처리 api 
 */
@RestController
public class ImageController {
	@Value("${file.location}")
	private String fileLocation;
	
	/**
	 * 이미지 획득 image_name 필요
	 * @param dto
	 * @return
	 * @throws IOException
	 */
	@PostMapping(
	value = "/upload/images", 
	//jpg, png, gif 이미지 데이터를 응답할 수 있도록 produces에 배열로 연결한다.
	produces= {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE,
	MediaType.IMAGE_GIF_VALUE}) //{경로변수}
	
	public byte[] image(@RequestBody ImageDto dto) throws IOException {
		//읽어드릴 파일 절대경로
		String absolutePath= fileLocation+File.separator+dto.getName();
		//파일에서 읽어들일 inputStream
		InputStream is =new FileInputStream(absolutePath);
		//commons io에 있는 IOUtils 클래스를 이용해서 이미지 파일에서 byte[]을 얻어낸다.
		return IOUtils.toByteArray(is);
	}
}
