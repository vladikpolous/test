package com.smartschool.schoolbackendproject.rest.admin;

import com.smartschool.schoolbackendproject.dto.*;
import com.smartschool.schoolbackendproject.model.Book;
import com.smartschool.schoolbackendproject.model.User;
import com.smartschool.schoolbackendproject.repository.BookRepository;
import com.smartschool.schoolbackendproject.repository.UserRepository;
import com.smartschool.schoolbackendproject.repository.VisitRepository;
import com.smartschool.schoolbackendproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "api/admin")
public class AdminRestControllerV1 {
    private final UserService userService;
    private final UserRepository userRepository;
    private final VisitRepository visitRepository;
    private final BookRepository bookRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AdminRestControllerV1(UserService userService,
                                 UserRepository userRepository,
                                 VisitRepository visitRepository,
                                 BookRepository bookRepository,
                                 BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.visitRepository = visitRepository;
        this.bookRepository = bookRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<UserForAdminDto> getUserById(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserForAdminDto result = UserForAdminDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/users/{id}/delete")
    public ResponseEntity deleteUser(@PathVariable(name = "id") Long id) {
        userService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/users/{id}/visits")
    public ResponseEntity<List<VisitDto>> getVisits(@PathVariable(name = "id") Long id) {
        User profileUser = userService.findById(id);

        if (profileUser == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<VisitDto> visitsList = VisitDto.fromListVisits(visitRepository.findAllByUser(profileUser));

        return new ResponseEntity<>(visitsList, HttpStatus.OK);
    }

    @GetMapping(value = "/users/{id}/books")
    public ResponseEntity<List<BookDto>> getBooks(@PathVariable(name = "id") Long id) {
        User profileUser = userService.findById(id);

        if (profileUser == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<BookDto> bookDtoList = BookDto.fromListBooks(bookRepository.findAllByUser(profileUser));

        return new ResponseEntity<>(bookDtoList, HttpStatus.OK);
    }

    @PostMapping(value = "/edit")
    public ResponseEntity editUser(@RequestBody EditForAdminDto editForAdminDto) {
        User profileUser = userService.findById(editForAdminDto.getId());

        if (profileUser == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        profileUser.setName(editForAdminDto.getName() != null ? editForAdminDto.getName() : profileUser.getName());
        profileUser.setMiddleName(editForAdminDto.getMiddleName() != null ? editForAdminDto.getMiddleName() : profileUser.getMiddleName());
        profileUser.setSurname(editForAdminDto.getSurname() != null ? editForAdminDto.getSurname() : profileUser.getSurname());
        profileUser.setEmail(editForAdminDto.getEmail() != null ? editForAdminDto.getEmail() : profileUser.getEmail());
        profileUser.setEmailParent1(editForAdminDto.getEmailParent1() != null ? editForAdminDto.getEmailParent1() : profileUser.getEmailParent1());
        profileUser.setEmailParent2(editForAdminDto.getEmailParent2() != null ? editForAdminDto.getEmailParent2() : profileUser.getEmailParent2());
        profileUser.setPassword(editForAdminDto.getPassword() != null ? passwordEncoder.encode(editForAdminDto.getPassword()) : profileUser.getPassword());
        userRepository.save(profileUser);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/books/add")
    public ResponseEntity addBookToUser(@RequestBody AddBookToUserDto addBookToUserDto) {
        User profileUser = userService.findById(addBookToUserDto.getId());

        if (profileUser == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Book book = new Book();
        book.setUser(profileUser);
        book.setBookName(addBookToUserDto.getBookName());
        book.setDateOfTaking(Timestamp.valueOf(LocalDateTime.now()));

        bookRepository.save(book);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
