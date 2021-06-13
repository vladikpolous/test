package com.smartschool.schoolbackendproject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.smartschool.schoolbackendproject.model.User;
import lombok.Data;

import java.sql.Timestamp;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserForAdminDto {
    private Long id;
    private String name;
    private String middleName;
    private String surname;
    private String email;
    private String emailParent1;
    private String emailParent2;
    private String classroom;
    private Timestamp created;
    private Timestamp updated;

    public User toUser() {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setMiddleName(middleName);
        user.setSurname(surname);
        user.setEmail(email);
        user.setEmailParent1(emailParent1);
        user.setEmailParent2(emailParent2);
        user.setClassroom(classroom);
        user.setCreated(created);
        user.setUpdated(updated);
        return user;
    }

    public static UserForAdminDto fromUser(User user) {
        UserForAdminDto userForAdminDto = new UserForAdminDto();
        userForAdminDto.setId(user.getId());
        userForAdminDto.setName(user.getName());
        userForAdminDto.setMiddleName(user.getMiddleName());
        userForAdminDto.setSurname(user.getSurname());
        userForAdminDto.setEmail(user.getEmail());
        userForAdminDto.setEmailParent1(user.getEmailParent1());
        userForAdminDto.setEmailParent2(user.getEmailParent2());
        userForAdminDto.setClassroom(user.getClassroom());
        userForAdminDto.setCreated(user.getCreated());
        userForAdminDto.setUpdated(user.getUpdated());
        return userForAdminDto;
    }
}
