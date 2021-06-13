package com.smartschool.schoolbackendproject.rest;

import com.smartschool.schoolbackendproject.dto.LoginAuthenticationRequestDto;
import com.smartschool.schoolbackendproject.dto.SignupAuthenticationRequestDto;
import com.smartschool.schoolbackendproject.model.User;
import com.smartschool.schoolbackendproject.security.jwt.JwtTokenProvider;
import com.smartschool.schoolbackendproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "api/auth")
public class AuthenticationRestControllerV1 {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    @Autowired
    public AuthenticationRestControllerV1(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<Object, Object>> login(@RequestBody LoginAuthenticationRequestDto requestDto) {
        String email = requestDto.getEmail();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, requestDto.getPassword()));
        User user = userService.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User with email: " + email + " not found");
        }

        String token = jwtTokenProvider.createToken(email, user.getRoles());

        Map<Object, Object> response = new HashMap<>();
        response.put("email", email);
        response.put("token", token);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody SignupAuthenticationRequestDto requestDto) {
        try {
            User newUser = new User();

            newUser.setName(requestDto.getName());
            newUser.setMiddleName(requestDto.getMiddleName());
            newUser.setSurname(requestDto.getSurname());
            newUser.setEmail(requestDto.getEmail());
            newUser.setEmailParent1(requestDto.getEmailParent1());
            newUser.setEmailParent2(requestDto.getEmailParent2());
            newUser.setPassword(requestDto.getPassword());
            newUser.setClassroom(requestDto.getClassroom());

            if (userService.register(newUser)) {
                LoginAuthenticationRequestDto variable = new LoginAuthenticationRequestDto();
                variable.setEmail(requestDto.getEmail());
                variable.setPassword(requestDto.getPassword());
                return login(variable);
            } else {
                return ResponseEntity.ok(HttpStatus.FORBIDDEN);
            }
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid email or password");
        }
    }
}
