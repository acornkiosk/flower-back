package com.acorn.flower.kiosk;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KioskServiceImpl implements KioskService {
	// 한 페이지에 몇개씩 표시할 것인지
	final int PAGE_ROW_COUNT = 10;
	// 하단 페이지를 몇개씩 표시할 것인지
	final int PAGE_DISPLAY_COUNT = 5;

	@Autowired
	private KioskDao dao;

	@Override
	public boolean insert(KioskDto dto) {
		int result = dao.insert(dto);

		if (result != 1)
			return false;

		return true;
	}

	@Override
	public boolean delete(KioskDto dto) {
		int result = dao.delete(dto);

		if (result != 1)
			return false;

		return true;
	}

	@Override
	public KioskDto getKiosk(KioskDto dto) {
		KioskDto result = dao.getKiosk(dto);

		if (result == null)
			return null;

		return result;
	}

	@Override
	public boolean update(KioskDto dto) {
		int result = dao.update(dto);

		if (result == 0)
			return false;

		return true;
	}

	@Override
	public KioskResponse selectPage(int pageNum) {
		// 보여줄 페이지의 시작 ROWNUM
		int startRowNum = 1 + (pageNum - 1) * PAGE_ROW_COUNT;
		// 보여줄 페이지의 끝 ROWNUM
		int endRowNum = pageNum * PAGE_ROW_COUNT;

		// startRowNum과 endRowNum을 KioskDto에 담고
		KioskDto dto = new KioskDto();
		dto.setStartRowNum(startRowNum);
		dto.setEndRowNum(endRowNum);

		List<KioskDto> list = dao.getList(dto);

		// 하단 시작 페이지 번호
		int startPageNum = 1 + ((pageNum - 1) / PAGE_DISPLAY_COUNT) * PAGE_DISPLAY_COUNT;
		// 하단 끝 페이지 번호
		int endPageNum = startPageNum + PAGE_DISPLAY_COUNT - 1;

		// 전체 row의 개수
		int totalRow = dao.getCount();
		// 전체 페이지의 갯수 구하기
		int totalPageCount = (int) Math.ceil(totalRow / (double) PAGE_ROW_COUNT);
		//끝 페이지 번호가 이미 전체 페이지 개수보다 크게 계산되었다면 잘못된 값
		if(endPageNum > totalPageCount) {
			endPageNum = totalPageCount; //보정해준다
		}
		
		KioskResponse res = new KioskResponse();
		res.setList(list);
		res.setStartPageNum(startPageNum);
		res.setEndPageNum(endPageNum);
		res.setTotalPageCount(totalPageCount);
		res.setPageNum(pageNum);
		return res;
	}

	@Override
	public List<KioskDto> getList(KioskDto dto) {
		List<KioskDto> list = dao.getList(dto);
		return list;
	}

}
