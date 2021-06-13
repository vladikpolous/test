package com.smartschool.schoolbackendproject.security.jwt;

import com.smartschool.schoolbackendproject.model.Role;
import com.smartschool.schoolbackendproject.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {
    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getName(),
                user.getMiddleName(),
                user.getSurname(),
                user.getEmail(),
                user.getEmailParent1(),
                user.getEmailParent2(),
                user.getPassword(),
                user.getClassroom(),
                user.getUpdated(),
                mapToGrantedAuthorities(new ArrayList<>(user.getRoles())));
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles) {
        return userRoles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
