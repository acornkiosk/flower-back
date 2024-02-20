package com.acorn.flower.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.acorn.flower.user.UserDao;
import com.acorn.flower.user.UserDto;

@Service //bean 으로 만들기 위해
public class CustomUserDetailsService implements UserDetailsService{
	
	
	//Spring Security 가 로그인 처리시 호출하는 메소드 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//2. UserDetails 객체에 해당정보를 담아서 리턴해 주어야 한다
		//권한은 1개 이지만 List 에 담아서 
		List<GrantedAuthority> authList=new ArrayList<>();
		//Authority 는 role 앞에  "ROLE_" 를 붙여주여야 한다.
		authList.add(new SimpleGrantedAuthority("ROLE_"+"SUPER"));
		
		//Spring Security 가 제공하는 User 클래스는 UserDetails 인터페이스를 구현한 클래스 이다. 
		UserDetails ud=new User("super", "1234", authList);
		
		return ud;
	}

}









