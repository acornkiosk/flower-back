package com.acorn.flower.superman;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.acorn.flower.jwt.JwtUtil;
import com.acorn.flower.user.UserDto;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
public class SuperController {


    //SecurityConfig 에서 Bean 으로 등록한 객체
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * token
     *
     * @param dto
     * @return
     */
    //JSON 문자열이 전송되면 @RequestBody 어노테이션을 이용해서 추출해야 한다
    @PostMapping("/api/auth")
    public String auth(@RequestBody UserDto dto,HttpServletResponse response,HttpServletRequest request) throws Exception {
        System.out.println(dto.getSave());
    	
        try {
            //입력한 username 과 password 를 인증토큰 객체에 담아서
            UsernamePasswordAuthenticationToken authToken =

                    new UsernamePasswordAuthenticationToken(dto.getId(), dto.getPassword());
            //인증 메니저 객체를 이용해서 인증을 진행한다
            authManager.authenticate(authToken);
        } catch (Exception e) {
            //예외가 발생하면 인증실패(아이디 혹은 비	밀번호 틀림 등등...)
            e.printStackTrace();
            throw new Exception("아이디 혹은 비밀번호가 틀려요!");
        }
        //예외가 발생하지 않고 여기까지 실행 된다면 인증을 통과 한 것이다. 토큰을 발급해서 응답한다.
     		//체크박스 체크할 경우 쿠키값을 생성한다.	
      		if("save".equals(dto.getSave())) {
      			//쿠키 생성하기
      			Cookie cookie=new Cookie("cid",dto.getId());
      			cookie.setMaxAge(60*60);
      			cookie.setPath("/");
      			response.addCookie(cookie);

      			System.out.println("yes:");
      			
      		}
      		//체크박스 체크 하지 않을 경우 쿠키값을 삭제해준다.
      		else {
      			//쿠키 삭제하기
      			Cookie cookie=new Cookie("cid","");
      			cookie.setMaxAge(0);
      			cookie.setPath("/");
      			response.addCookie(cookie);
      			
      			System.out.println("no:");
      		}
        String token = jwtUtil.generateToken(dto.getId());
        return token;
    }

}
