package com.acorn.flower.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 *  직원 관리
 *  @author "오영찬", "이준호"
 *  @version 1.0.0
 *  @date 2024.02.17
 */

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService service;

    /**
     * 직원관리 페이지로 이동
     * @return
     */
    @GetMapping("/api/owner/user/index")
    public List<UserDto> userIndex(){
        List<UserDto> list = service.getUserList();
        return list;
    }

    /**
     * 직원 리스트 조회
     * @return
     */
    @GetMapping("/api/owner/user/list")
    public ResponseEntity<UserResponse> getUserList() {
        System.out.println("asdfsadfasdf");
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
     * 직원 조회
     * @param id
     * @return
     */
    @PostMapping("/api/onwer/user/get")
    public ResponseEntity<UserResponse> getUser(@RequestBody String id) {
        UserResponse response = new UserResponse();
        try {
            int userId = Integer.parseInt(id);
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
     * 직원 삭제
     * @param id
     * @return
     */
    @PostMapping("/api/owner/user/delete")
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
         * 직원 추가
         * @param dto
         * @return
         */
        @PostMapping("/api/owner/user/add")
        public ResponseEntity<UserResponse> userAdd(@RequestBody UserDto dto) {
            boolean isSuccess;
            UserResponse response = new UserResponse();
            try {
                isSuccess = service.insert(dto);
                if (isSuccess) {
                    log.info("user = {}", dto.toString());
                    response.setDto(dto);
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
     * 직원 정보 수정
     * @param dto
     * @return
     */
    @PostMapping("/api/owner/user/update")
    public ResponseEntity<UserResponse> userUpdate(@RequestBody UserDto dto) {
        UserResponse response = new UserResponse();
        try {
            boolean isSuccess = service.updateUser(dto);
            if (isSuccess) {
                log.info(dto.getUserId() + "직원 정보가 변경 되었습니다.");
                UserDto updateDto = service.getUser(dto.getUserId());
                response.setDto(updateDto);
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