package com.smartschool.schoolbackendproject.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "email_parent1")
    private String emailParent1;

    @Column(name = "email_parent2")
    private String emailParent2;

    @Column(name = "password")
    private String password;

    @Column(name = "classroom")
    private String classroom;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "id_user", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "id_role", referencedColumnName = "id")})
    private List<Role> roles;

    public User() {
    }

    public User(String name, String middleName, String surname, String email, String emailParent1, String emailParent2, String password, String classroom) {
        this.name = name;
        this.middleName = middleName;
        this.surname = surname;
        this.email = email;
        this.emailParent1 = emailParent1;
        this.emailParent2 = emailParent2;
        this.password = password;
        this.classroom = classroom;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", middleName='" + middleName + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", emailParent1='" + emailParent1 + '\'' +
                ", emailParent2='" + emailParent2 + '\'' +
                ", password='" + password + '\'' +
                ", classroom='" + classroom +
                '}';
    }
}
