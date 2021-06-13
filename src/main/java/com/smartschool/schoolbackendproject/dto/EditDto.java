package com.smartschool.schoolbackendproject.dto;

import lombok.Data;

@Data
public class EditDto {
    private String name;
    private String middleName;
    private String surname;
    private String email;
    private String emailParent1;
    private String emailParent2;
    private String password;
}
