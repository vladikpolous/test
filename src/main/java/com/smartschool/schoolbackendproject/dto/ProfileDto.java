package com.smartschool.schoolbackendproject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.smartschool.schoolbackendproject.model.User;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ProfileDto {
    private Long id;
    private String name;
    private String middleName;
    private String surname;
    private String email;
    private String emailParent1;
    private String emailParent2;
    private String password;
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
        user.setPassword(password);
        user.setClassroom(classroom);
        user.setCreated(created);
        user.setUpdated(updated);
        return user;
    }

    public static ProfileDto fromUser(User user) {
        ProfileDto profileDto = new ProfileDto();
        profileDto.setId(user.getId());
        profileDto.setName(user.getName());
        profileDto.setMiddleName(user.getMiddleName());
        profileDto.setSurname(user.getSurname());
        profileDto.setEmail(user.getEmail());
        profileDto.setEmailParent1(user.getEmailParent1());
        profileDto.setEmailParent2(user.getEmailParent2());
        profileDto.setPassword(user.getPassword());
        profileDto.setClassroom(user.getClassroom());
        profileDto.setCreated(user.getCreated());
        profileDto.setUpdated(user.getUpdated());
        return profileDto;
    }
}
