package com.onkar.hospitalManagement.security;

import com.onkar.hospitalManagement.dto.LoginRequestDto;
import com.onkar.hospitalManagement.dto.LoginResponseDto;
import com.onkar.hospitalManagement.dto.SignUpRequestDto;
import com.onkar.hospitalManagement.dto.SignUpResponseDto;
import com.onkar.hospitalManagement.entity.User;
import com.onkar.hospitalManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthUtil authUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<LoginResponseDto> login(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getUsername(),
                        loginRequestDto.getPassword()
                )
        );

        User user = (User) authentication.getPrincipal();
        String token = authUtil.generateAccessToken(user);

        return ResponseEntity.ok(new LoginResponseDto(token, user.getId()));
    }

    public SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto) {

        if (userRepository.findByUsername(signUpRequestDto.getUsername()).isPresent()) {
            throw new IllegalArgumentException("User already exists!");
        }

        if (signUpRequestDto.getPassword() == null || signUpRequestDto.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password cannot be empty!");
        }

        User newUser = new User(
                signUpRequestDto.getUsername(),
                passwordEncoder.encode(signUpRequestDto.getPassword())  // Encrypt password before saving
        );

        User savedUser = userRepository.save(newUser);
        return new SignUpResponseDto(savedUser.getId(), savedUser.getUsername());
    }
}
