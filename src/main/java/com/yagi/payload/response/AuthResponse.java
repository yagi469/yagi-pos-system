package com.yagi.payload.response;

import com.yagi.payload.dto.UserDto;

import lombok.Data;

@Data
public class AuthResponse {

    private String jwt;
    private String message;
    private UserDto user;

}
