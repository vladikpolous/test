package com.smartschool.schoolbackendproject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.smartschool.schoolbackendproject.model.User;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private String name;
    private String middleName;
    private String surname;
    private String classroom;

    public User toUser(){
        User user = new User();
        user.setName(name);
        user.setMiddleName(middleName);
        user.setSurname(surname);
        user.setClassroom(classroom);

        return user;
    }

    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setMiddleName(user.getMiddleName());
        userDto.setSurname(user.getSurname());
        userDto.setClassroom(user.getClassroom());

        return userDto;
    }
}
