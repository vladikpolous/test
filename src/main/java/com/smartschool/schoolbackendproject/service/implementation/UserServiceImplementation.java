package com.smartschool.schoolbackendproject.service.implementation;

import com.smartschool.schoolbackendproject.model.Role;
import com.smartschool.schoolbackendproject.model.User;
import com.smartschool.schoolbackendproject.repository.RoleRepository;
import com.smartschool.schoolbackendproject.repository.UserRepository;
import com.smartschool.schoolbackendproject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImplementation(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public boolean register(User user) {

        try {
            Role roleUser = roleRepository.findByName("ROLE_USER");
            List<Role> userRoles = new ArrayList<>();
            userRoles.add(roleUser);

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(userRoles);

            user.setCreated(Timestamp.valueOf(LocalDateTime.now()));
            user.setUpdated(Timestamp.valueOf(LocalDateTime.now()));

            User registeredUser = userRepository.save(user);

            log.info("IN UserServiceImplementation.register - user: {} successfully registered", registeredUser);

            return true;
        } catch (Exception exception) {
            log.info("IN UserServiceImplementation.register ERROR");

            return false;
        }


    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        log.info("IN UserServiceImplementation.getAll - {} users found", result.size());
        return result;
    }

    @Override
    public User findByEmail(String email) {
        User result = userRepository.findByEmail(email);
        log.info("IN UserServiceImplementation.findByEmail - user: {} found by email: {}", result, email);
        return result;
    }

    @Override
    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);

        if (result == null) {
            log.warn("IN UserServiceImplementation.findById - no user found by id: {}", id);
            return null;
        }

        log.info("IN UserServiceImplementation.findById - user: {} found by id: {}", result);
        return result;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("IN UserServiceImplementation.delete - user with id: {} successfully deleted");
    }
}
