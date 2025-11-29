package com.yagi.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.yagi.configuration.JwtProvider;
import com.yagi.domain.UserRole;
import com.yagi.exception.UserException;
import com.yagi.mapper.UserMapper;
import com.yagi.model.User;
import com.yagi.payload.dto.UserDto;
import com.yagi.payload.response.AuthResponse;
import com.yagi.repository.UserRepository;
import com.yagi.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomUserImplementation customUserImplementation;

    @Override
    public AuthResponse signup(UserDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail());

        if (user != null) {
            throw new UserException("email already registered!");
        }

        if (UserRole.ROLE_ADMIN.equals(userDto.getRole())) {
            throw new UserException("role admin is not allowed!");
        }

        User newUser = new User();
        newUser.setEmail(userDto.getEmail());
        newUser.setFullName(userDto.getFullName());
        newUser.setPhone(userDto.getPhone());
        newUser.setRole(userDto.getRole());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        newUser.setCreatedAt(LocalDateTime.now());
        newUser.setLastLogin(LocalDateTime.now());

        User savedUser = userRepository.save(newUser);

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(savedUser.getRole().toString()));
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDto.getEmail(),
                userDto.getPassword(), authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("User registered successfully");
        authResponse.setUser(UserMapper.toDTO(savedUser));

        return authResponse;
    }

    @Override
    public AuthResponse login(UserDto userDto) {

        String email = userDto.getEmail();
        String password = userDto.getPassword();

        Authentication authentication = authenticate(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        User user = userRepository.findByEmail(email);
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("User login successfully");
        authResponse.setUser(UserMapper.toDTO(user));
        return authResponse;
    }

    private Authentication authenticate(String email, String password) {

        UserDetails userDetails = customUserImplementation.loadUserByUsername(email);
        if (userDetails == null) {
            throw new UserException("Email doesn't exist : " + email);
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new UserException("Password doesn't match");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }
}
