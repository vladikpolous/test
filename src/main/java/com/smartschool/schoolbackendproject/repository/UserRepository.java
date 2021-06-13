package com.smartschool.schoolbackendproject.repository;

import com.smartschool.schoolbackendproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
