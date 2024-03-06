package com.acorn.flower.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
	final int PAGE_ROW_COUNT = 10;
	final int PAGE_DISPLAY_COUNT = 5;

	@Autowired
	private UserDao dao;

	@Override
	public List<UserDto> getUserList(UserDto dto) {
		List<UserDto> list = dao.getUserList(dto);
		return list;
	}

	@Override
	public UserResponse selectPage(int pageNum) {
		int startRowNum = 1 + (pageNum-1) * PAGE_ROW_COUNT;
		int endRowNum = pageNum * PAGE_ROW_COUNT;

		UserDto dto = new UserDto();
		dto.setStartRowNum(startRowNum);
		dto.setEndRowNum(endRowNum);

		List<UserDto> list = dao.getUserList(dto);
		int startPageNum = 1 + ((pageNum-1)/PAGE_DISPLAY_COUNT) * PAGE_DISPLAY_COUNT;
		int endPageNum = startPageNum + PAGE_DISPLAY_COUNT -1;

		int totalRow = dao.getCount();
		int totalPageCount = (int)Math.ceil(totalRow / (double)PAGE_ROW_COUNT);
		if(endPageNum > totalPageCount){
			endPageNum = totalPageCount;
		}

		UserResponse response = new UserResponse();
		response.setList(list);
		response.setStartPageNum(startPageNum);
		response.setEndPageNum(endPageNum);
		response.setTotalPageCount(totalPageCount);
		response.setPageNum(pageNum);

		return response;
	}

	@Override
	public UserDto getUser(String id) {
		UserDto dto = dao.getUser(id);
		return dto;
	}

	@Override
	public boolean insert(UserDto dto) {
		int result = dao.insert(dto);

		if (result != 1)
			return false;

		return true;
	}


	@Override
	public boolean delete(String id) {
		int result = dao.delete(id);

		if (result != 1)
			return false;

		return true;
	}

	@Override
	public boolean updateUser(UserDto dto) {
		int result = dao.update(dto);

		if (result == 0)
			return false;

		return true;
	}


}