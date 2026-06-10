package com.example.qlsv_springboot.service;

import com.example.qlsv_springboot.dto.LoginRequest;
import com.example.qlsv_springboot.dto.LoginResponse;
import com.example.qlsv_springboot.dto.RegisterRequest;

public interface AuthService {

    LoginResponse login(LoginRequest request);

    LoginResponse register(RegisterRequest request);
}