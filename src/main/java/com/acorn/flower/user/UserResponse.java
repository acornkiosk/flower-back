package com.acorn.flower.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponse {
    private List<UserDto> list;
    private UserDto dto;
    private int startPageNum;
    private int endPageNum;
    private int totalPageCount;
    private int pageNum;
    private HttpStatus status;
    private boolean hasID;
}
