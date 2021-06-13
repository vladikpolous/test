package com.smartschool.schoolbackendproject.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "visits")
@Data
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;

    @Column(name = "date_of_arrival")
    private Timestamp dateOfArrival;

    @Column(name = "date_of_leave")
    private Timestamp dateOfLeave;

    @Override
    public String toString() {
        return "Visits{" +
                "user=" + user +
                ", dateOfArrival=" + dateOfArrival +
                ", dateOfLeave=" + dateOfLeave +
                '}';
    }
}
