package com.acorn.flower.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service // bean 으로 만들기 위해
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private UserDao dao;

	// Spring Security 가 로그인 처리시 호출하는 메소드
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		// 1. form 에 입력한 userName 을 이용해서 사용자의 자세한 정보를 얻어온다.
		UserDto dto = dao.getUser(id);
		// 만일 저장된 userName 이 없다면
		if (dto == null) {
			System.out.println("사용자 정보 없음");
			// 예외를 발생시킨다
			throw new UsernameNotFoundException("존재하지 않는 사용자 입니다");
		}

		// 2. UserDetails 객체에 해당정보를 담아서 리턴해 주어야 한다
		// DB 비밀번호(1234) 이기때문에 아래 해시화 해야한다. test임
		

		// 권한은 1개 이지만 List 에 담아서
		List<GrantedAuthority> authList = new ArrayList<>();
		// Authority 는 role 앞에 "ROLE_" 를 붙여주여야 한다.
		authList.add(new SimpleGrantedAuthority("ROLE_super"));


		// Spring Security 가 제공하는 User 클래스는 UserDetails 인터페이스를 구현한 클래스 이다.
		UserDetails ud = new User(dto.getId(), dto.getPassword(), authList);

		// 암호화

		return ud;
	}
}
