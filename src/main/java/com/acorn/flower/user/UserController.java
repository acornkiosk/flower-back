package com.acorn.flower.user;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.acorn.flower.jwt.JwtUtil;

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
    private UserService service;

    /**
     * list all members of user
     * @return
     */
    @PostMapping("/api/user/list")
    public ResponseEntity<UserResponse> getUserList(@RequestBody UserDto dto) {
        UserResponse response = new UserResponse();
        try {
            List<UserDto> list = service.getUserList(dto);
            if (!list.isEmpty()) {
                for (UserDto tmp : list) {
                    log.info("user = {}", tmp.toString());
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
    public ResponseEntity<UserResponse> getUser(@RequestBody UserDto dto) {
        UserResponse response = new UserResponse();
        try {
            UserDto result = service.getUser(dto.getId());
            if (result != null) {
                log.info("user = {}", result.toString());
                response.setDto(result);
                response.setStatus(HttpStatus.OK);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                log.error(dto.getId() + "번 직원은 없습니다.");
                response.setStatus(HttpStatus.BAD_REQUEST);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            log.error("서버에 문제가 있습니다.");
            e.printStackTrace();
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
        	e.printStackTrace();
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
    public ResponseEntity<UserResponse> userDelete(@RequestBody UserDto dto) {
        boolean isSuccess;
        UserResponse response = new UserResponse();
        try {
            UserDto result = service.getUser(dto.getId());
            isSuccess = service.delete(dto);
            if (isSuccess) {
                log.info(dto.getId() + "번 삭제되었습니다.");
                response.setDto(result);
                response.setStatus(HttpStatus.OK);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                log.error(dto.getId() + "번은 없는 데이터입니다.");
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


    

	

}