package com.acorn.flower.user;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.acorn.flower.util.JwtUtil;

import lombok.extern.slf4j.Slf4j;

/**
 *  user management
 *  @author "Younchan Oh", "Junho Lee"
 *  @version 1.0.0
 *  @date 2024.02.17
 */

@Slf4j
@RestController
public class UserController {
	@Autowired
	private JwtUtil jwtUtil;
	//SecurityConfig 에서 Bean 으로 등록한 객체
	@Autowired
	private AuthenticationManager authManager;
	
    @Autowired
    private UserService service;

    /**
     * list all members of user
     * @return
     */
    @GetMapping("/api/user/list")
    public ResponseEntity<UserResponse> getUserList() {
        UserResponse response = new UserResponse();
        try {
            List<UserDto> list = service.getUserList();
            if (!list.isEmpty()) {
                for (UserDto dto : list) {
                    log.info("user = {}", dto.toString());
                }
                response.setList(list);
                response.setStatus(HttpStatus.OK);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                log.error("직원 데이터를 불러오는데 실패했습니다.");
                response.setStatus(HttpStatus.BAD_REQUEST);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            log.error("서버에 문제가 있습니다.");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * find a user by id
     * @param id
     * @return
     */
    @PostMapping("/api/user/get")
    public ResponseEntity<UserResponse> getUser(@RequestBody String id) {
        UserResponse response = new UserResponse();
        try {
            UserDto dto = service.getUser(id);
            if (dto != null) {
                log.info("user = {}", dto.toString());
                response.setDto(dto);
                response.setStatus(HttpStatus.OK);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                log.error(id + "번 직원은 없습니다.");
                response.setStatus(HttpStatus.BAD_REQUEST);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            log.error("서버에 문제가 있습니다.");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * create a new user, find a user by dto
     * @param dto
     * @return
     */
    @PostMapping("/api/user/add")
    public ResponseEntity<UserResponse> userAdd(@RequestBody UserDto dto) {
        boolean isSuccess;
        UserResponse response = new UserResponse();
        try {
            isSuccess = service.insert(dto);
            UserDto insertedDto = service.getUser(dto.getId());
            if (isSuccess) {
                log.info("user = {}", insertedDto.toString());
                response.setDto(insertedDto);
                response.setStatus(HttpStatus.OK);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                log.error("추가되지 않았습니다.");
                response.setDto(dto);
                response.setStatus(HttpStatus.BAD_REQUEST);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            log.error("서버에 문제가 있습니다.");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * delete user, find a user by id
     * @param id
     * @return
     */
    @PostMapping("/api/user/delete")
    public ResponseEntity<UserResponse> userDelete(@RequestBody String id) {
        boolean isSuccess;
        UserResponse response = new UserResponse();
        try {
            UserDto dto = service.getUser(id);
            isSuccess = service.delete(id);
            if (isSuccess) {
                log.info(id + "번 삭제되었습니다.");
                response.setDto(dto);
                response.setStatus(HttpStatus.OK);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                log.error(id + "번은 없는 데이터입니다.");
                response.setStatus(HttpStatus.BAD_REQUEST);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            log.error("서버에 문제가 있습니다.");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * update user, find a user by dto
     * @param dto
     * @return
     */
    @PostMapping("/api/user/update")
    public ResponseEntity<UserResponse> userUpdate(@RequestBody UserDto dto) {
        UserResponse response = new UserResponse();
        try {
            boolean isSuccess = service.updateUser(dto);
            if (isSuccess) {
                log.info(dto.getId() + "직원 정보가 변경 되었습니다.");
                UserDto updatedDto = service.getUser(dto.getId());
                response.setDto(updatedDto);
                response.setStatus(HttpStatus.OK);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                log.error("직원 정보 변경에 실패했습니다.");
                response.setDto(dto);
                response.setStatus(HttpStatus.BAD_REQUEST);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            log.error("서버에 문제가 있습니다.");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    /**
     * token
     * @param dto
     * @return
     */
	//JSON 문자열이 전송되면 @RequestBody 어노테이션을 이용해서 추출해야 한다 
	@PostMapping("api/auth")
	public String auth(@RequestBody UserDto dto) throws Exception {
		try {
			//입력한 username 과 password 를 인증토큰 객체에 담아서 
			UsernamePasswordAuthenticationToken authToken=

					new UsernamePasswordAuthenticationToken(dto.getId(), dto.getPassword());	
			//인증 메니저 객체를 이용해서 인증을 진행한다 
			authManager.authenticate(authToken);
		}catch(Exception e) {
			//예외가 발생하면 인증실패(아이디 혹은 비밀번호 틀림 등등...)
			e.printStackTrace();
			throw new Exception("아이디 혹은 비밀번호가 틀려요!");
		}
		//예외가 발생하지 않고 여기까지 실행 된다면 인증을 통과 한 것이다. 토큰을 발급해서 응답한다.

		String token=jwtUtil.generateToken(dto.getUserName());
		return token;
	}
	
	@GetMapping("/ping")
	public String ping() {
		return "pong";
	}
}