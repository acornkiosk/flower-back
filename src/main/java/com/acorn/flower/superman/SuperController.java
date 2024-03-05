package com.acorn.flower.superman;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.acorn.flower.jwt.JwtUtil;
import com.acorn.flower.user.UserDto;
import com.acorn.flower.user.UserResponse;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
public class SuperController {
	

	//SecurityConfig 에서 Bean 으로 등록한 객체
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtUtil jwtUtil;

	
    @Autowired
    private SuperService service;
    
    
    /**
     * token
     * @param dto
     * @return
     */
	//JSON 문자열이 전송되면 @RequestBody 어노테이션을 이용해서 추출해야 한다 
	@PostMapping("/api/auth")
	public String auth(@RequestBody UserDto dto) throws Exception {
		System.out.println(dto.getId());
		System.out.println(dto.getPassword());
		try {
			//입력한 username 과 password 를 인증토큰 객체에 담아서 
			UsernamePasswordAuthenticationToken authToken=

					new UsernamePasswordAuthenticationToken(dto.getId(), dto.getPassword());	
			//인증 메니저 객체를 이용해서 인증을 진행한다 
			authManager.authenticate(authToken);
		}catch(Exception e) {
			//예외가 발생하면 인증실패(아이디 혹은 비	밀번호 틀림 등등...)
			e.printStackTrace();
			throw new Exception("아이디 혹은 비밀번호가 틀려요!");
		}
		//예외가 발생하지 않고 여기까지 실행 된다면 인증을 통과 한 것이다. 토큰을 발급해서 응답한다.

		String token=jwtUtil.generateToken(dto.getId());
		return token;
	}
}
