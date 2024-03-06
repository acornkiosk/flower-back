package com.acorn.flower.menu;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MenuServiceImpl implements MenuService {
	@Autowired
	private MenuDao menuDao;

	// 한 페이지에 몇개씩 표시할 것인지
	final int PAGE_ROW_COUNT = 10;
	// 하단 페이지를 몇개씩 표시할 것인지
	final int PAGE_DISPLAY_COUNT = 5;

	/** 파일을 저장할 위치 */
	@Value("${file.location}")
	private String fileLocation;

	@Override
	public boolean insert(MenuDto dto) {
		// 선택된 이미지 파일을 가져와서
		MultipartFile image = dto.getImage();
		// image.getSize()!=0
		if (image.getSize() != 0) {
			// fileLocation에 원본이미지 이름의 형태로 저장한다.
			// 그럼 확장자는 어떻게? , 같은 이름의 이미지는 중복해서 넣을 수 없다
			String saveFileName = dto.getImage().getOriginalFilename();
			// 저장할 파일의 전체 경로 구성하기
			String filePath = fileLocation + File.separator + saveFileName;
			try {
				// 업로드된 파일을 이동시킬 목적지 File 객체
				File f = new File(filePath);
				// MultipartFile 객체의 메소드를 통해서 실제로 이동시키기(전송하기)
				dto.getImage().transferTo(f);
			} catch (Exception e) {
				e.printStackTrace();
			}
			dto.setImg_name(saveFileName);
		}
		int result = menuDao.insert(dto);
		if (result != 1)
			return false;
		return true;

	}

	@Override
	public MenuResponse getList(MenuDto dto) {
		int pageNum = dto.getPageNum();
		// 보여줄 페이지의 시작 ROWNUM
		int startRowNum = 1 + (pageNum - 1) * PAGE_ROW_COUNT;
		// 보여줄 페이지의 끝 ROWNUM
		int endRowNum = pageNum * PAGE_ROW_COUNT;

		// startRowNum 과 endRowNum 을 MenuDto 객체에 담고
		dto.setStartRowNum(startRowNum);
		dto.setEndRowNum(endRowNum);

		List<MenuDto> list = menuDao.getList(dto);

		// 하단 시작 페이지 번호
		int startPageNum = 1 + ((pageNum - 1) / PAGE_DISPLAY_COUNT) * PAGE_DISPLAY_COUNT;
		// 하단 끝 페이지 번호
		int endPageNum = startPageNum + PAGE_DISPLAY_COUNT - 1;

		// 전체 row 의 갯수
		int totalRow = menuDao.getCount(dto);
		// 전체 페이지의 갯수 구하기
		int totalPageCount = (int) Math.ceil(totalRow / (double) PAGE_ROW_COUNT);
		// 끝 페이지 번호가 이미 전체 페이지 갯수보다 크게 계산되었다면 잘못된 값이다.
		if (endPageNum > totalPageCount) {
			endPageNum = totalPageCount; // 보정해 준다.
		}

		MenuResponse response = new MenuResponse();
		response.setList(list);
		response.setEndPageNum(endPageNum);
		response.setPageNum(pageNum);
		response.setStartPageNum(startPageNum);
		response.setTotalPageCount(totalPageCount);

		return response;

	}

	@Override
	public MenuDto getMenu(MenuDto dto) {
		MenuDto result = menuDao.getMenu(dto);
		if (result == null)
			return null;
		return result;
	}

	@Override
	public boolean update(MenuDto dto) {
		MultipartFile image = dto.getImage();
		if (image.getSize() != 0) {
			// fileLocation에 원본이미지 이름의 형태로 저장한다.
			// 그럼 확장자는 어떻게? , 같은 이름의 이미지는 중복해서 넣을 수 없다
			String saveFileName = dto.getImage().getOriginalFilename();
			// 저장할 파일의 전체 경로 구성하기
			String filePath = fileLocation + File.separator + saveFileName;
			try {
				// 업로드된 파일을 이동시킬 목적지 File 객체
				File f = new File(filePath);
				// MultipartFile 객체의 메소드를 통해서 실제로 이동시키기(전송하기)
				dto.getImage().transferTo(f);
			} catch (Exception e) {
				e.printStackTrace();
			}
			dto.setImg_name(saveFileName);
		}
		int result = menuDao.update(dto);
		if (result != 1)
			return false;
		return true;
	}

	@Override
	public boolean delete(MenuDto dto) {
		int result = menuDao.delete(dto);
		if (result != 1)
			return false;
		return true;
	}

}
