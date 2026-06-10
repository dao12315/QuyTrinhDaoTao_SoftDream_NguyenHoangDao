package com.example.qlsv_springboot.service.impl;

import com.example.qlsv_springboot.dto.LoginRequest;
import com.example.qlsv_springboot.dto.LoginResponse;
import com.example.qlsv_springboot.dto.RegisterRequest;
import com.example.qlsv_springboot.entity.User;
import com.example.qlsv_springboot.repository.UserRepository;
import com.example.qlsv_springboot.security.JwtUtil;
import com.example.qlsv_springboot.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Email không tồn tại"));

        if (!request.getPassword().equals(user.getPassword())) {
            throw new RuntimeException("Mật khẩu không chính xác");
        }

        String token = jwtUtil.generateToken(user);

        return new LoginResponse(
                user.getId(),
                user.getEmail(),
                user.getRole(),
                token,
                "Đăng nhập thành công"
        );
    }

    @Override
    public LoginResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email đã tồn tại");
        }

        if (!request.getRole().equals("student") && !request.getRole().equals("teacher")) {
            throw new RuntimeException("Role chỉ được là student hoặc teacher");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());
        user.setCreatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(user);

        String token = jwtUtil.generateToken(savedUser);

        return new LoginResponse(
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getRole(),
                token,
                "Tạo tài khoản thành công"
        );
    }
}