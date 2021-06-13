package com.smartschool.schoolbackendproject.dto;

import com.smartschool.schoolbackendproject.model.User;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class AddBookToUserDto {
    private Long id;
    private String bookName;
}
