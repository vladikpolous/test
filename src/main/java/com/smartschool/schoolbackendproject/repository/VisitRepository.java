package com.smartschool.schoolbackendproject.repository;

import com.smartschool.schoolbackendproject.model.User;
import com.smartschool.schoolbackendproject.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long> {
    List<Visit> findAllByUser(User user);
}
