package com.smartschool.schoolbackendproject.dto;

import lombok.Data;

@Data
public class SignupAuthenticationRequestDto {
    private String name;
    private String middleName;
    private String surname;
    private String email;
    private String emailParent1;
    private String emailParent2;
    private String password;
    private String classroom;
}
