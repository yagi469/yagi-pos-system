package com.yagi.service;

import com.yagi.payload.dto.UserDto;
import com.yagi.payload.response.AuthResponse;

public interface AuthService {
    AuthResponse signup(UserDto userDto);

    AuthResponse login(UserDto userDto);
}
