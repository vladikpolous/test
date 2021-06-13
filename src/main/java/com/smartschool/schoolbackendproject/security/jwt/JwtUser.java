package com.smartschool.schoolbackendproject.security.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

public class JwtUser implements UserDetails {

    private final Long id;
    private final String name;
    private final String middleName;
    private final String surname;
    private final String email;
    private final String emailParent1;
    private final String emailParent2;
    private final String password;
    private final String classroom;
    private final Date lastPasswordResetDate;
    private final Collection<? extends GrantedAuthority> authorities;

    public JwtUser(
            Long id,
            String name,
            String middleName,
            String surname,
            String email,
            String emailParent1,
            String emailParent2,
            String password,
            String classroom,
            Date lastPasswordResetDate,
            Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.name = name;
        this.middleName = middleName;
        this.surname = surname;
        this.email = email;
        this.emailParent1 = emailParent1;
        this.emailParent2 = emailParent2;
        this.password = password;
        this.classroom = classroom;
        this.lastPasswordResetDate = lastPasswordResetDate;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    @JsonIgnore
    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public String getName() {
        return name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getEmailParent1() {
        return emailParent1;
    }

    public String getEmailParent2() {
        return emailParent2;
    }

    public String getClassroom() {
        return classroom;
    }
}
