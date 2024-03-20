package com.acorn.flower.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
	// 한 페이지에 몇개씩 표시할 것인지
	final int PAGE_ROW_COUNT = 10;
	// 하단 페이지를 몇개씩 표시할 것인지
	final int PAGE_DISPLAY_COUNT = 5;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	@Autowired
	private UserDao dao;

	@Override
	public List<UserDto> getUserList(UserDto dto) {
		List<UserDto> list = dao.getUserList(dto);
		return list;
	}

	@Override
	public UserDto getUser(String id) {
		UserDto result = dao.getUser(id);
		return result;
	}

	@Override
	public boolean insert(UserDto dto) {
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		dto.setPassword(encoder.encode(dto.getPassword()));
		int result = dao.insert(dto);

		if (result != 1)
			return false;

		return true;
	}

	@Override
	public boolean delete(UserDto dto) {
		int result = dao.delete(dto);

		if (result != 1)
			return false;

		return true;
	}

	@Override
	public boolean updateUser(UserDto dto) {
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if(dto.getNewPassword() != null) {
		dto.setNewPassword(encoder.encode(dto.getNewPassword()));
		}
		int result = dao.update(dto);

		if (result == 0)
			return false;

		return true;
	}

	@Override
	public UserResponse selectPage(UserDto dto) {
		int pageNum = dto.getPageNum();
		// 보여줄 페이지의 시작 ROWNUM
		int startRowNum = 1 + (pageNum - 1) * PAGE_ROW_COUNT;
		// 보여줄 페이지의 끝 ROWNUM
		int endRowNum = pageNum * PAGE_ROW_COUNT;

		UserResponse res = new UserResponse();

		if (pageNum != 0) {
			// 하단 시작 페이지 번호
			int startPageNum = 1 + ((pageNum - 1) / PAGE_DISPLAY_COUNT) * PAGE_DISPLAY_COUNT;
			// 하단 끝 페이지 번호
			int endPageNum = startPageNum + PAGE_DISPLAY_COUNT - 1;

			// 전체 row의 개수
			int totalRow = dao.getCount();
			// 전체 페이지의 갯수 구하기
			int totalPageCount = (int) Math.ceil(totalRow / (double) PAGE_ROW_COUNT);
			// 끝 페이지 번호가 이미 전체 페이지 개수보다 크게 계산되었다면 잘못된 값
			if (endPageNum > totalPageCount) {
				endPageNum = totalPageCount; // 보정해준다
			}
			dto.setStartRowNum(startRowNum);
			dto.setEndRowNum(endRowNum);
			res.setStartPageNum(startPageNum);
			res.setEndPageNum(endPageNum);
			res.setTotalPageCount(totalPageCount);
		}
		

		List<UserDto> list = dao.getUserList(dto);
		
		res.setList(list);
		res.setPageNum(pageNum);
		return res;
	}
	
	@Override
	public boolean checkId(UserDto dto) {
		String result = dao.checkId(dto);
		if (result == null)
			return true;

		return false;
	}
}