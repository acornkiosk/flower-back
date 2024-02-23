package com.acorn.flower.user;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

//서비스클래스는 @Service 어노테이션을 이용해서 bean 으로 만든다 
@Service
public class UserServiceImpl implements UserService{
	//서비스는 dao 를 이용해서 로직 처리를 한다.
	@Autowired
	private UserDao dao;
	//비밀번호 암호화하는 객체도 bean 으로 등록이 되어 있다.
	@Autowired
	private PasswordEncoder encoder;
	//업로드된 이미지를 저장할 파일시스템 상의 위치 
	@Value("${file.location}")
	private String fileLocation;
	
	@Override
	public void addUser(UserDto dto) {
		//암호화된 비밀 번호를 얻어내서 
		String encodedPwd=encoder.encode(dto.getPassword());
		//dto 에 덮어쓰기 한다음
		dto.setPassword(encodedPwd);
		//일반 사용자라는 의미에서 role 에 "USER" 를 넣어준다.
		dto.setRank("USER");
		//DB 에 저장한다.
		dao.insert(dto);
	}

	@Override
	public void getInfo(Model model) {
		//로그인된 userName
		String id=SecurityContextHolder.getContext().getAuthentication().getName();
		//사용자 정보를 읽어와서 
		UserDto dto=dao.getUser(id);
		//Model 객체에 담는다
		model.addAttribute("dto", dto);
	}

}
