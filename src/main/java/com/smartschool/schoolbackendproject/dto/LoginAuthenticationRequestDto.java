package com.smartschool.schoolbackendproject.dto;

import lombok.Data;

@Data
public class LoginAuthenticationRequestDto {
    private String email;
    private String password;
}
