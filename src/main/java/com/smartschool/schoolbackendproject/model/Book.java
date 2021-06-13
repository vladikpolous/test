package com.smartschool.schoolbackendproject.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "books")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "date_of_taking")
    private Timestamp dateOfTaking;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", user=" + user +
                ", bookName='" + bookName + '\'' +
                ", dateOfTaking=" + dateOfTaking +
                '}';
    }
}
