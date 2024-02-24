package com.acorn.flower.menu;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MenuServiceImpl implements MenuService {
	@Autowired
	private MenuDao menuDao;
	
	/** 파일을 저장할 위치 */
	@Value("${file.location}") 
	private String fileLocation;

	@Override
	public boolean insert(MenuDto dto) {
		// 선택된 이미지 파일을 가져와서		
		MultipartFile image=dto.getImage();
		//image.getSize()!=0
		if(image!=null){
			//fileLocation에 원본이미지 이름의 형태로 저장한다.
			//그럼 확장자는 어떻게? , 같은 이름의 이미지는 중복해서 넣을 수 없다
			String saveFileName=dto.getImage().getOriginalFilename();
			//저장할 파일의 전체 경로 구성하기
			String filePath=fileLocation+File.separator+saveFileName;
			try {
				//업로드된 파일을 이동시킬 목적지 File 객체
				File f=new File(filePath);
				//MultipartFile 객체의 메소드를 통해서 실제로 이동시키기(전송하기)
				dto.getImage().transferTo(f);
			}catch(Exception e) {
				e.printStackTrace();
			}
			dto.setImg_name(saveFileName);
		}
		int result =menuDao.insert(dto);
		if(result!=1) return false;
		return true;
	}

	@Override
	public List<MenuDto> getList(MenuDto dto) {
		List<MenuDto> list= menuDao.getList(dto);
		return list;
	}

	@Override
	public MenuDto getMenu(MenuDto dto) {
		MenuDto result= menuDao.getMenu(dto);
		if(result ==null) return null;
		return dto;
	}

	@Override
	public boolean update(MenuDto dto) {
		MultipartFile image=dto.getImage();
		if(image!=null){
			//fileLocation에 원본이미지 이름의 형태로 저장한다.
			//그럼 확장자는 어떻게? , 같은 이름의 이미지는 중복해서 넣을 수 없다
			String saveFileName=dto.getImage().getOriginalFilename();
			//저장할 파일의 전체 경로 구성하기
			String filePath=fileLocation+File.separator+saveFileName;
			try {
				//업로드된 파일을 이동시킬 목적지 File 객체
				File f=new File(filePath);
				//MultipartFile 객체의 메소드를 통해서 실제로 이동시키기(전송하기)
				dto.getImage().transferTo(f);
			}catch(Exception e) {
				e.printStackTrace();
			}
			dto.setImg_name(saveFileName);
		}
		int result =menuDao.update(dto);
		if(result!=1) return false;
		return true;
	}

	@Override
	public boolean delete(MenuDto dto) {
		int result =menuDao.delete(dto);
		if(result!=1) return false;
		return true;
	}


}
