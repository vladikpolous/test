package com.smartschool.schoolbackendproject.rest;

import com.smartschool.schoolbackendproject.dto.BookDto;
import com.smartschool.schoolbackendproject.dto.EditDto;
import com.smartschool.schoolbackendproject.dto.ProfileDto;
import com.smartschool.schoolbackendproject.dto.VisitDto;
import com.smartschool.schoolbackendproject.model.User;
import com.smartschool.schoolbackendproject.repository.BookRepository;
import com.smartschool.schoolbackendproject.repository.UserRepository;
import com.smartschool.schoolbackendproject.repository.VisitRepository;
import com.smartschool.schoolbackendproject.security.jwt.JwtTokenProvider;
import com.smartschool.schoolbackendproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "api/profile")
public class ProfileRestControllerV1 {
    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final VisitRepository visitRepository;
    private final BookRepository bookRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public ProfileRestControllerV1(
            UserService userService,
            JwtTokenProvider jwtTokenProvider,
            VisitRepository visitRepository,
            BookRepository bookRepository,
            UserRepository userRepository,
            BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.visitRepository = visitRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(value = "/my")
    public ResponseEntity<ProfileDto> getYourOwnProfile(HttpServletRequest req){
        String email = jwtTokenProvider.getEmail(jwtTokenProvider.resolveToken(req));
        User profileUser = userService.findByEmail(email);

        if (profileUser == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        ProfileDto profileDto = ProfileDto.fromUser(profileUser);

        return new ResponseEntity<>(profileDto, HttpStatus.OK);
    }

    @GetMapping(value = "/visits")
    public ResponseEntity<List<VisitDto>> getVisits(HttpServletRequest req){
        User profileUser = jwtTokenProvider.getUserByJwtToken(req);

        if (profileUser == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<VisitDto> visitsList = VisitDto.fromListVisits(visitRepository.findAllByUser(profileUser));

        return new ResponseEntity<>(visitsList, HttpStatus.OK);
    }

    @GetMapping(value = "/books")
    public ResponseEntity<List<BookDto>> getBooks(HttpServletRequest req){
        User profileUser = jwtTokenProvider.getUserByJwtToken(req);

        if (profileUser == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<BookDto> bookDtoList = BookDto.fromListBooks(bookRepository.findAllByUser(profileUser));

        return new ResponseEntity<>(bookDtoList, HttpStatus.OK);
    }

    @PostMapping(value = "/edit")
    public ResponseEntity editProfile(HttpServletRequest req, @RequestBody EditDto editDto){
        User profileUser = jwtTokenProvider.getUserByJwtToken(req);

        if (profileUser == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        profileUser.setName(editDto.getName() != null ? editDto.getName() : profileUser.getName());
        profileUser.setMiddleName(editDto.getMiddleName() != null ? editDto.getMiddleName() : profileUser.getMiddleName());
        profileUser.setSurname(editDto.getSurname() != null ? editDto.getSurname() : profileUser.getSurname());
        profileUser.setEmail(editDto.getEmail() != null ? editDto.getEmail() : profileUser.getEmail());
        profileUser.setEmailParent1(editDto.getEmailParent1() != null ? editDto.getEmailParent1() : profileUser.getEmailParent1());
        profileUser.setEmailParent2(editDto.getEmailParent2() != null ? editDto.getEmailParent2() : profileUser.getEmailParent2());
        profileUser.setPassword(editDto.getPassword() != null ? passwordEncoder.encode(editDto.getPassword()) : profileUser.getPassword());
        userRepository.save(profileUser);

//        Long idUser = profileUser.getId();

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
