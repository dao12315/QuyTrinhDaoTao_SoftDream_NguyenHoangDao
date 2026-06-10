package com.example.qlsv_springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {

    private Integer userId;

    private String email;

    private String role;

    private String token;

    private String message;
}