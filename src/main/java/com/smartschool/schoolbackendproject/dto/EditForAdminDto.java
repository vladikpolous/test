package com.smartschool.schoolbackendproject.dto;

import lombok.Data;

@Data
public class EditForAdminDto {
    private Long id;
    private String name;
    private String middleName;
    private String surname;
    private String email;
    private String emailParent1;
    private String emailParent2;
    private String password;
}
