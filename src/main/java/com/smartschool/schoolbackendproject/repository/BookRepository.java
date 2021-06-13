package com.smartschool.schoolbackendproject.repository;

import com.smartschool.schoolbackendproject.model.Book;
import com.smartschool.schoolbackendproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByUser(User user);
}
