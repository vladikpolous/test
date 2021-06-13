package com.smartschool.schoolbackendproject.service;

import com.smartschool.schoolbackendproject.model.User;

import java.util.List;

public interface UserService {
    boolean register(User user);

    List<User> getAll();

    User findByEmail(String email);

    User findById(Long id);

    void delete(Long id);
}
