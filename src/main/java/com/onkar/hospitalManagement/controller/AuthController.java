package com.onkar.hospitalManagement.controller;

import com.onkar.hospitalManagement.dto.LoginRequestDto;
import com.onkar.hospitalManagement.dto.LoginResponseDto;
import com.onkar.hospitalManagement.dto.SignUpRequestDto;
import com.onkar.hospitalManagement.dto.SignUpResponseDto;
import com.onkar.hospitalManagement.security.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private  AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
        return ResponseEntity.ok(authService.login(loginRequestDto).getBody());
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto){
        return ResponseEntity.ok(authService.signUp(signUpRequestDto));
    }
}
