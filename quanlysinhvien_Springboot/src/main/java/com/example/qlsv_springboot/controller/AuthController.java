package com.example.qlsv_springboot.controller;

import com.example.qlsv_springboot.dto.LoginRequest;
import com.example.qlsv_springboot.dto.LoginResponse;
import com.example.qlsv_springboot.dto.RegisterRequest;
import com.example.qlsv_springboot.security.JwtUtil;
import com.example.qlsv_springboot.service.AuthService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/register")
    public LoginResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @GetMapping("/me")
    public Claims me(@RequestHeader("Authorization") String authorization) {
        String token = authorization.replace("Bearer ", "");
        return jwtUtil.getClaimsFromToken(token);
    }
}